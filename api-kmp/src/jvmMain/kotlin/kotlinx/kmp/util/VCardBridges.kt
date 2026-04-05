package kotlinx.kmp.util

/**
 * vCard bridges: ezvcard.VCard ↔ Smack XEP-0054 VCard ↔ Bedework bw-ical4j-vcard.
 *
 * Our primary vCard model is ezvcard.VCard (full RFC 6350). Wire VCardContact
 * provides cross-KMP serialization. These bridges let you interop with:
 *
 * - **Smack VCardManager** (XMPP XEP-0054) — hard-coded subset of RFC 2426.
 *   Missing properties fall through to `setField(name, value)` escape hatch.
 *
 * - **Bedework bw-ical4j-vcard** (CardDAV server-side model) — uses
 *   `net.fortuna.ical4j.vcard.VCard` with a generic `Property` hierarchy.
 *
 * All bridges are reflection-free: both libs are compileOnly deps. If the
 * user's classpath doesn't include them, importing this file compiles fine
 * but calling the bridge functions throws NoClassDefFoundError at runtime.
 */
object VCardBridges {

    // === Smack XEP-0054 VCard bridge ===

    /**
     * Convert ezvcard.VCard → Smack vcard-temp VCard.
     *
     * Smack only supports the XEP-0054 subset:
     * FN, N (first/middle/last/prefix/suffix), NICKNAME, EMAIL (home/work),
     * TEL (home/work with type), ORG, ORG-UNIT, ADR (home/work), TITLE, ROLE,
     * JABBERID, PHOTO/AVATAR, BDAY (as field), DESC (as NOTE).
     *
     * All other RFC 6350 properties (IMPP except JABBERID, LANG, TZ, GEO,
     * MEMBER, RELATED, PRODID, REV, SOUND, UID, KEY, FBURL, CAL*, etc.)
     * are written via `setField(name, value)` — Smack's generic escape hatch.
     */
    @JvmStatic
    fun ezvcardToSmack(vcard: ezvcard.VCard): org.jivesoftware.smackx.vcardtemp.packet.VCard {
        val smack = org.jivesoftware.smackx.vcardtemp.packet.VCard()

        // §6.2.2 N (StructuredName) — Smack has typed setters
        vcard.structuredName?.let { n ->
            n.given?.let { smack.firstName = it }
            n.family?.let { smack.lastName = it }
            n.additionalNames?.firstOrNull()?.let { smack.middleName = it }
            n.prefixes?.firstOrNull()?.let { smack.prefix = it }
            n.suffixes?.firstOrNull()?.let { smack.suffix = it }
        }
        vcard.formattedName?.value?.let { smack.setField("FN", it) }

        // §6.2.3 NICKNAME
        vcard.nickname?.values?.firstOrNull()?.let { smack.nickName = it }

        // §6.4.2 EMAIL — Smack splits home/work by parameter
        vcard.emails?.forEach { email ->
            val types = email.types?.map { it.value.uppercase() } ?: emptyList()
            when {
                "WORK" in types -> smack.emailWork = email.value
                "HOME" in types || types.isEmpty() -> smack.emailHome = email.value
                else -> smack.setField("EMAIL;TYPE=${types.joinToString(",")}", email.value)
            }
        }

        // §6.4.1 TEL — Smack splits home/work with type string
        vcard.telephoneNumbers?.forEach { tel ->
            val types = tel.types?.map { it.value.uppercase() } ?: emptyList()
            val typeKey = when {
                "CELL" in types -> "CELL"
                "FAX" in types -> "FAX"
                "VOICE" in types -> "VOICE"
                else -> "VOICE"
            }
            val number = tel.text ?: tel.uri?.toString() ?: return@forEach
            if ("WORK" in types) smack.setPhoneWork(typeKey, number)
            else smack.setPhoneHome(typeKey, number)
        }

        // §6.6.4 ORG
        vcard.organization?.let { org ->
            org.values?.firstOrNull()?.let { smack.organization = it }
            org.values?.drop(1)?.firstOrNull()?.let { smack.organizationUnit = it }
        }

        // §6.6.1/2 TITLE / ROLE
        vcard.titles?.firstOrNull()?.value?.let { smack.setField("TITLE", it) }
        vcard.roles?.firstOrNull()?.value?.let { smack.setField("ROLE", it) }

        // §6.3.1 ADR — Smack splits home/work with field map
        vcard.addresses?.forEach { addr ->
            val types = addr.types?.map { it.value.uppercase() } ?: emptyList()
            val isWork = "WORK" in types
            val put: (String, String) -> Unit = { k, v ->
                if (isWork) smack.setAddressFieldWork(k, v) else smack.setAddressFieldHome(k, v)
            }
            addr.streetAddress?.let { put("STREET", it) }
            addr.extendedAddress?.let { put("EXTADR", it) }
            addr.locality?.let { put("LOCALITY", it) }
            addr.region?.let { put("REGION", it) }
            addr.postalCode?.let { put("PCODE", it) }
            addr.country?.let { put("CTRY", it) }
            addr.poBox?.let { put("POBOX", it) }
        }

        // §6.4.3 IMPP — Smack has JABBERID field; others go to setField
        vcard.impps?.forEach { impp ->
            val uri = impp.uri?.toString() ?: return@forEach
            if (uri.startsWith("xmpp:")) smack.setJabberId(uri.removePrefix("xmpp:"))
            else smack.setField("IMPP", uri)
        }

        // §6.2.4 PHOTO — Smack's avatar API (bytes + mime)
        vcard.photos?.firstOrNull()?.let { photo ->
            val data = photo.data
            if (data != null) {
                smack.setAvatar(data, photo.contentType?.mediaType ?: "image/jpeg")
            } else photo.url?.let { smack.setField("PHOTO", it) }
        }

        // §6.7.2 NOTE → DESC (XEP-0054)
        vcard.notes?.firstOrNull()?.value?.let { smack.setField("DESC", it) }

        // §6.2.5 BDAY (Smack escape hatch — no typed setter)
        vcard.birthday?.date?.let { smack.setField("BDAY", it.toString()) }

        // Remaining RFC 6350 properties → setField escape hatch (XMPP extensions)
        vcard.uid?.value?.let { smack.setField("UID", it) }
        vcard.urls?.firstOrNull()?.value?.let { smack.setField("URL", it) }
        vcard.timezones?.firstOrNull()?.text?.let { smack.setField("TZ", it) }
        vcard.geos?.firstOrNull()?.let { smack.setField("GEO", "${it.latitude};${it.longitude}") }
        vcard.languages?.firstOrNull()?.value?.let { smack.setField("LANG", it) }
        vcard.gender?.gender?.let { smack.setField("GENDER", it) }
        vcard.categories?.values?.let { smack.setField("CATEGORIES", it.joinToString(",")) }
        vcard.productId?.value?.let { smack.setField("PRODID", it) }
        vcard.revision?.value?.let { smack.setField("REV", it.toString()) }
        vcard.keys?.firstOrNull()?.url?.let { smack.setField("KEY", it) }
        vcard.fbUrls?.firstOrNull()?.value?.let { smack.setField("FBURL", it) }
        vcard.calendarUris?.firstOrNull()?.value?.let { smack.setField("CALURI", it) }

        return smack
    }

    /**
     * Convert Smack vcard-temp VCard → ezvcard.VCard (full RFC 6350).
     *
     * Typed fields are mapped to ezvcard properties; anything set via
     * setField() that maps to a standard RFC 6350 property name is promoted.
     */
    @JvmStatic
    fun smackToEzvcard(smack: org.jivesoftware.smackx.vcardtemp.packet.VCard): ezvcard.VCard {
        val vcard = ezvcard.VCard()

        // N + FN
        if (!smack.firstName.isNullOrBlank() || !smack.lastName.isNullOrBlank()) {
            val sn = ezvcard.property.StructuredName()
            sn.given = smack.firstName
            sn.family = smack.lastName
            smack.middleName?.let { sn.additionalNames.add(it) }
            smack.prefix?.let { sn.prefixes.add(it) }
            smack.suffix?.let { sn.suffixes.add(it) }
            vcard.structuredName = sn
        }
        (smack.getField("FN") ?: listOfNotNull(smack.firstName, smack.lastName).joinToString(" "))
            .takeIf { it.isNotBlank() }?.let { vcard.setFormattedName(it) }

        smack.nickName?.takeIf { it.isNotBlank() }?.let {
            val nn = ezvcard.property.Nickname()
            nn.values.add(it)
            vcard.addNickname(nn)
        }

        // EMAIL
        smack.emailHome?.takeIf { it.isNotBlank() }?.let {
            val e = ezvcard.property.Email(it)
            e.types.add(ezvcard.parameter.EmailType.HOME)
            vcard.addEmail(e)
        }
        smack.emailWork?.takeIf { it.isNotBlank() }?.let {
            val e = ezvcard.property.Email(it)
            e.types.add(ezvcard.parameter.EmailType.WORK)
            vcard.addEmail(e)
        }

        // ORG
        if (!smack.organization.isNullOrBlank() || !smack.organizationUnit.isNullOrBlank()) {
            val o = ezvcard.property.Organization()
            smack.organization?.let { o.values.add(it) }
            smack.organizationUnit?.let { o.values.add(it) }
            vcard.addOrganization(o)
        }

        smack.getField("TITLE")?.let { vcard.addTitle(it) }
        smack.getField("ROLE")?.let { vcard.addRole(it) }
        smack.getField("DESC")?.let { vcard.addNote(it) }
        smack.getField("BDAY")?.let {
            try { vcard.setBirthday(java.time.LocalDate.parse(it)) } catch (_: Exception) {}
        }
        smack.getField("UID")?.let { vcard.uid = ezvcard.property.Uid(it) }
        smack.getField("URL")?.let { vcard.addUrl(it) }
        smack.getField("TZ")?.let { vcard.addTimezone(ezvcard.property.Timezone(it)) }
        smack.getField("LANG")?.let { vcard.addLanguage(ezvcard.property.Language(it)) }
        smack.getField("GENDER")?.let { vcard.gender = ezvcard.property.Gender(it) }
        smack.getField("PRODID")?.let { vcard.productId = ezvcard.property.ProductId(it) }
        smack.getField("KEY")?.let {
            try { vcard.addKey(ezvcard.property.Key(it, ezvcard.parameter.KeyType.PGP)) } catch (_: Exception) {}
        }
        smack.getField("FBURL")?.let { vcard.addFbUrl(ezvcard.property.FreeBusyUrl(it)) }
        smack.getField("CALURI")?.let { vcard.addCalendarUri(ezvcard.property.CalendarUri(it)) }
        smack.getField("CATEGORIES")?.split(",")?.takeIf { it.isNotEmpty() }?.let { cats ->
            val c = ezvcard.property.Categories()
            cats.forEach { c.values.add(it.trim()) }
            vcard.addCategories(c)
        }
        smack.getField("GEO")?.split(";")?.takeIf { it.size == 2 }?.let { (lat, lon) ->
            try { vcard.addGeo(ezvcard.property.Geo(lat.toDouble(), lon.toDouble())) } catch (_: Exception) {}
        }

        // IMPP — JABBERID is Smack-specific
        smack.jabberId?.takeIf { it.isNotBlank() }?.let {
            try { vcard.addImpp(ezvcard.property.Impp("xmpp:$it")) } catch (_: Exception) {}
        }

        // Avatar → PHOTO
        smack.avatar?.let { bytes ->
            val photo = ezvcard.property.Photo(bytes, ezvcard.parameter.ImageType.JPEG)
            vcard.addPhoto(photo)
        }

        // ADR home/work
        val homeFields = listOf("STREET", "EXTADR", "LOCALITY", "REGION", "PCODE", "CTRY", "POBOX")
            .mapNotNull { f -> smack.getAddressFieldHome(f)?.let { f to it } }.toMap()
        if (homeFields.isNotEmpty()) {
            val adr = ezvcard.property.Address()
            adr.types.add(ezvcard.parameter.AddressType.HOME)
            homeFields["STREET"]?.let { adr.streetAddress = it }
            homeFields["EXTADR"]?.let { adr.extendedAddress = it }
            homeFields["LOCALITY"]?.let { adr.locality = it }
            homeFields["REGION"]?.let { adr.region = it }
            homeFields["PCODE"]?.let { adr.postalCode = it }
            homeFields["CTRY"]?.let { adr.country = it }
            homeFields["POBOX"]?.let { adr.poBox = it }
            vcard.addAddress(adr)
        }
        val workFields = listOf("STREET", "EXTADR", "LOCALITY", "REGION", "PCODE", "CTRY", "POBOX")
            .mapNotNull { f -> smack.getAddressFieldWork(f)?.let { f to it } }.toMap()
        if (workFields.isNotEmpty()) {
            val adr = ezvcard.property.Address()
            adr.types.add(ezvcard.parameter.AddressType.WORK)
            workFields["STREET"]?.let { adr.streetAddress = it }
            workFields["EXTADR"]?.let { adr.extendedAddress = it }
            workFields["LOCALITY"]?.let { adr.locality = it }
            workFields["REGION"]?.let { adr.region = it }
            workFields["PCODE"]?.let { adr.postalCode = it }
            workFields["CTRY"]?.let { adr.country = it }
            workFields["POBOX"]?.let { adr.poBox = it }
            vcard.addAddress(adr)
        }

        // TEL home/work with types
        listOf("VOICE", "CELL", "FAX").forEach { type ->
            smack.getPhoneHome(type)?.takeIf { it.isNotBlank() }?.let {
                val t = ezvcard.property.Telephone(it)
                t.types.add(ezvcard.parameter.TelephoneType.HOME)
                when (type) {
                    "CELL" -> t.types.add(ezvcard.parameter.TelephoneType.CELL)
                    "FAX" -> t.types.add(ezvcard.parameter.TelephoneType.FAX)
                    "VOICE" -> t.types.add(ezvcard.parameter.TelephoneType.VOICE)
                }
                vcard.addTelephoneNumber(t)
            }
            smack.getPhoneWork(type)?.takeIf { it.isNotBlank() }?.let {
                val t = ezvcard.property.Telephone(it)
                t.types.add(ezvcard.parameter.TelephoneType.WORK)
                when (type) {
                    "CELL" -> t.types.add(ezvcard.parameter.TelephoneType.CELL)
                    "FAX" -> t.types.add(ezvcard.parameter.TelephoneType.FAX)
                    "VOICE" -> t.types.add(ezvcard.parameter.TelephoneType.VOICE)
                }
                vcard.addTelephoneNumber(t)
            }
        }

        return vcard
    }

    // === Bedework bw-ical4j-vcard bridge ===
    //
    // Direct Property.Id → Wire VCardContact field mapping (no text round-trip).
    // Each net.fortuna.ical4j.vcard.Property is dispatched to its matching
    // Wire field via its Property.Id enum value.

    /**
     * Bedework bw-ical4j-vcard → Wire VCardContact via direct Property.Id mapping.
     * Iterates the ical4j VCard's Property list and populates Wire fields per RFC 6350.
     */
    @JvmStatic
    fun bedeworkToWire(vcard: net.fortuna.ical4j.vcard.VCard): VCardContact {
        // Accumulators — Wire VCardContact is immutable, build everything first
        var name: PersonName? = null
        val nicknames = mutableListOf<String>()
        val emails = mutableListOf<String>()
        val phones = mutableListOf<com.google.type.PhoneNumber>()
        val addresses = mutableListOf<com.google.type.PostalAddress>()
        val impps = mutableListOf<String>()
        val languages = mutableListOf<String>()
        val urls = mutableListOf<String>()
        val categories = mutableListOf<String>()
        val members = mutableListOf<String>()
        val related = mutableListOf<VCardRelated>()
        val orgUnits = mutableListOf<String>()
        val expertise = mutableListOf<String>()
        val hobbies = mutableListOf<String>()
        val interests = mutableListOf<String>()
        val orgDirectories = mutableListOf<String>()
        val extensions = mutableMapOf<String, String>()
        var source = ""; var kind = ""; var xml = ""
        var photo = ""; var gender = ""
        var organization = ""; var title = ""; var role = ""; var logo = ""
        var timezone = ""; var geo: com.google.type.LatLng? = null
        var note = ""; var prodid = ""; var rev = ""; var sound = ""; var uid = ""
        var key = ""; var fburl = ""; var calAdrUri = ""; var calUri = ""
        var birthPlace = ""; var deathPlace = ""

        val idEnum = net.fortuna.ical4j.vcard.Property.Id::class.java
        val ID = { n: String -> try { java.lang.Enum.valueOf(idEnum, n) } catch (_: Exception) { null } }
        val FN = ID("FN"); val N_ID = ID("N"); val NICKNAME = ID("NICKNAME"); val PHOTO = ID("PHOTO")
        val BDAY = ID("BDAY"); val GENDER = ID("GENDER"); val ADR = ID("ADR"); val TEL = ID("TEL")
        val EMAIL = ID("EMAIL"); val IMPP = ID("IMPP"); val LANG = ID("LANG"); val TZ = ID("TZ")
        val GEO = ID("GEO"); val TITLE = ID("TITLE"); val ROLE = ID("ROLE"); val LOGO = ID("LOGO")
        val ORG = ID("ORG"); val MEMBER = ID("MEMBER"); val RELATED = ID("RELATED")
        val CATEGORIES = ID("CATEGORIES"); val NOTE = ID("NOTE"); val PRODID = ID("PRODID")
        val REV = ID("REV"); val SOUND = ID("SOUND"); val UID = ID("UID"); val URL = ID("URL")
        val KEY = ID("KEY"); val FBURL = ID("FBURL"); val CALADRURI = ID("CALADRURI")
        val CALURI = ID("CALURI"); val SOURCE = ID("SOURCE"); val KIND = ID("KIND")
        val XML = ID("XML"); val BIRTH = ID("BIRTH"); val DEATH = ID("DEATH")
        val EXTENDED = ID("EXTENDED")

        var fnValue = ""
        var nValue = ""

        for (prop in vcard.properties) {
            val id = prop.id
            val value = prop.value ?: ""
            when (id) {
                SOURCE -> source = value
                KIND -> kind = value
                XML -> xml = value
                FN -> fnValue = value
                N_ID -> nValue = value  // "family;given;additional;prefix;suffix"
                NICKNAME -> value.split(",").forEach { nicknames.add(it.trim()) }
                PHOTO -> photo = value
                GENDER -> gender = value
                ADR -> {
                    // ical4j ADR structured value: pobox;ext;street;locality;region;postalCode;country
                    val parts = value.split(";")
                    addresses.add(com.google.type.PostalAddress(
                        address_lines = listOfNotNull(parts.getOrNull(2)?.takeIf { it.isNotBlank() }),
                        locality = parts.getOrNull(3) ?: "",
                        administrative_area = parts.getOrNull(4) ?: "",
                        postal_code = parts.getOrNull(5) ?: "",
                        region_code = parts.getOrNull(6) ?: "",
                    ))
                }
                TEL -> phones.add(com.google.type.PhoneNumber(e164_number = value))
                EMAIL -> emails.add(value)
                IMPP -> impps.add(value)
                LANG -> languages.add(value)
                TZ -> timezone = value
                GEO -> {
                    val parts = value.split(";", ",")
                    if (parts.size == 2) {
                        geo = com.google.type.LatLng(
                            latitude = parts[0].toDoubleOrNull() ?: 0.0,
                            longitude = parts[1].toDoubleOrNull() ?: 0.0,
                        )
                    }
                }
                TITLE -> title = value
                ROLE -> role = value
                LOGO -> logo = value
                ORG -> {
                    val parts = value.split(";")
                    organization = parts.firstOrNull() ?: ""
                    parts.drop(1).forEach { orgUnits.add(it) }
                }
                MEMBER -> members.add(value)
                RELATED -> {
                    val type = prop.getParameter(
                        net.fortuna.ical4j.vcard.Parameter.Id.TYPE
                    )?.value ?: ""
                    related.add(VCardRelated(uri = value, type = type))
                }
                CATEGORIES -> value.split(",").forEach { categories.add(it.trim()) }
                NOTE -> note = value
                PRODID -> prodid = value
                REV -> rev = value
                SOUND -> sound = value
                UID -> uid = value
                URL -> urls.add(value)
                KEY -> key = value
                FBURL -> fburl = value
                CALADRURI -> calAdrUri = value
                CALURI -> calUri = value
                BIRTH -> birthPlace = value
                DEATH -> deathPlace = value
                EXTENDED -> extensions[prop.extendedName ?: "X-UNKNOWN"] = value
                else -> {
                    // Unhandled ID — store as extension for safety
                    extensions[id?.toString() ?: "UNKNOWN"] = value
                }
            }
        }

        // Compose PersonName from N + FN
        if (nValue.isNotBlank()) {
            val p = nValue.split(";")
            name = PersonName(
                family = p.getOrNull(0) ?: "",
                given = p.getOrNull(1) ?: "",
                middle = p.getOrNull(2) ?: "",
                prefix = p.getOrNull(3) ?: "",
                suffix = p.getOrNull(4) ?: "",
            )
        } else if (fnValue.isNotBlank()) {
            val p = fnValue.split(" ", limit = 2)
            name = PersonName(given = p.getOrNull(0) ?: "", family = p.getOrNull(1) ?: "")
        }

        return VCardContact(
            source = source, kind = kind, xml = xml,
            name = name, nicknames = nicknames, photo = photo, gender = gender,
            addresses = addresses,
            phones = phones, emails = emails, impp = impps, languages = languages,
            timezone = timezone, geo = geo,
            title = title, role = role, logo = logo,
            organization = organization, org_units = orgUnits,
            members = members, related = related,
            urls = urls, categories = categories, note = note,
            prodid = prodid, rev = rev, sound = sound, uid = uid,
            key = key, fburl = fburl, cal_adr_uri = calAdrUri, cal_uri = calUri,
            birth_place = birthPlace, death_place = deathPlace,
            expertise = expertise, hobbies = hobbies, interests = interests,
            org_directories = orgDirectories,
            extensions = extensions,
        ).dedup()
    }

    /**
     * Wire VCardContact → Bedework bw-ical4j-vcard via direct Property construction.
     * Each Wire field maps to a net.fortuna.ical4j.vcard.property.* instance.
     */
    @JvmStatic
    fun wireToBedework(contact: VCardContact): net.fortuna.ical4j.vcard.VCard {
        val props = mutableListOf<net.fortuna.ical4j.vcard.Property>()

        // FN + N
        contact.name?.let { n ->
            if (n.full.isNotBlank()) {
                props.add(net.fortuna.ical4j.vcard.property.Fn(n.full))
            }
            if (n.given.isNotBlank() || n.family.isNotBlank()) {
                props.add(net.fortuna.ical4j.vcard.property.N(
                    n.family, n.given,
                    if (n.middle.isNotBlank()) arrayOf(n.middle) else emptyArray(),
                    if (n.prefix.isNotBlank()) arrayOf(n.prefix) else emptyArray(),
                    if (n.suffix.isNotBlank()) arrayOf(n.suffix) else emptyArray(),
                ))
            }
        }

        // Identification
        if (contact.nicknames.isNotEmpty()) {
            props.add(net.fortuna.ical4j.vcard.property.Nickname(*contact.nicknames.toTypedArray()))
        }
        if (contact.photo.isNotBlank()) {
            props.add(net.fortuna.ical4j.vcard.property.Photo(java.net.URI.create(contact.photo)))
        }
        if (contact.gender.isNotBlank()) {
            props.add(net.fortuna.ical4j.vcard.property.Gender(contact.gender))
        }

        // Delivery
        contact.addresses.forEach { a ->
            props.add(net.fortuna.ical4j.vcard.property.Address(
                "", "",
                a.address_lines.firstOrNull() ?: "",
                a.locality,
                a.administrative_area,
                a.postal_code,
                a.region_code,
            ))
        }

        // Communications
        contact.phones.forEach { p ->
            val num = p.e164_number ?: p.short_code?.number ?: ""
            if (num.isNotBlank()) {
                props.add(net.fortuna.ical4j.vcard.property.Telephone(num))
            }
        }
        contact.emails.forEach { props.add(net.fortuna.ical4j.vcard.property.Email(it)) }
        contact.impp.forEach {
            try { props.add(net.fortuna.ical4j.vcard.property.Impp(java.net.URI.create(it))) } catch (_: Exception) {}
        }
        contact.languages.forEach {
            try { props.add(net.fortuna.ical4j.vcard.property.Lang(java.util.Locale.forLanguageTag(it))) } catch (_: Exception) {}
        }

        // Geographical
        if (contact.timezone.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.Tz(contact.timezone)) } catch (_: Exception) {}
        }
        contact.geo?.let {
            props.add(net.fortuna.ical4j.vcard.property.Geo(
                java.math.BigDecimal.valueOf(it.latitude),
                java.math.BigDecimal.valueOf(it.longitude),
            ))
        }

        // Organizational
        if (contact.title.isNotBlank()) props.add(net.fortuna.ical4j.vcard.property.Title(contact.title))
        if (contact.role.isNotBlank()) props.add(net.fortuna.ical4j.vcard.property.Role(contact.role))
        if (contact.logo.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.Logo(java.net.URI.create(contact.logo))) } catch (_: Exception) {}
        }
        if (contact.organization.isNotBlank() || contact.org_units.isNotEmpty()) {
            val orgParts = (listOf(contact.organization) + contact.org_units).filter { it.isNotBlank() }
            props.add(net.fortuna.ical4j.vcard.property.Org(*orgParts.toTypedArray()))
        }
        contact.members.forEach {
            try { props.add(net.fortuna.ical4j.vcard.property.Member(java.net.URI.create(it))) } catch (_: Exception) {}
        }
        contact.related.forEach { rel ->
            try { props.add(net.fortuna.ical4j.vcard.property.Related(java.net.URI.create(rel.uri))) } catch (_: Exception) {}
        }

        // Explanatory
        contact.urls.forEach {
            try { props.add(net.fortuna.ical4j.vcard.property.Url(java.net.URI.create(it))) } catch (_: Exception) {}
        }
        if (contact.categories.isNotEmpty()) {
            props.add(net.fortuna.ical4j.vcard.property.Categories(*contact.categories.toTypedArray()))
        }
        if (contact.note.isNotBlank()) props.add(net.fortuna.ical4j.vcard.property.Note(contact.note))
        if (contact.prodid.isNotBlank()) props.add(net.fortuna.ical4j.vcard.property.ProdId(contact.prodid))
        if (contact.sound.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.Sound(java.net.URI.create(contact.sound))) } catch (_: Exception) {}
        }
        if (contact.uid.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.Uid(java.net.URI.create(contact.uid))) } catch (_: Exception) {}
        }

        // Security
        if (contact.key.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.Key(java.net.URI.create(contact.key))) } catch (_: Exception) {}
        }

        // Calendar
        if (contact.fburl.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.FbUrl(java.net.URI.create(contact.fburl))) } catch (_: Exception) {}
        }
        if (contact.cal_adr_uri.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.CalAdrUri(java.net.URI.create(contact.cal_adr_uri))) } catch (_: Exception) {}
        }
        if (contact.cal_uri.isNotBlank()) {
            try { props.add(net.fortuna.ical4j.vcard.property.CalUri(java.net.URI.create(contact.cal_uri))) } catch (_: Exception) {}
        }

        return net.fortuna.ical4j.vcard.VCard(props)
    }

    // === Wire VCardContact convenience bridges (commonMain → JVM libs) ===

    /** Wire VCardContact → Smack VCard via ezvcard. */
    @JvmStatic
    fun wireToSmack(contact: VCardContact): org.jivesoftware.smackx.vcardtemp.packet.VCard =
        ezvcardToSmack(Validators.wireToEzvcard(contact))

    /** Smack VCard → Wire VCardContact via ezvcard. */
    @JvmStatic
    fun smackToWire(smack: org.jivesoftware.smackx.vcardtemp.packet.VCard): VCardContact =
        Validators.ezvcardToWire(smackToEzvcard(smack))
}
