package kmp.apigen

import org.junit.jupiter.api.Test
import org.assertj.core.api.Assertions.assertThat

/**
 * Master-detail + FK navigation tests per target binding.
 *
 * Verifies that generated master-detail layouts:
 * 1. Split into master (list) + detail (selected) panels
 * 2. FK properties render as clickable navigation links
 * 3. Each target (Material3/WebDOM) produces correct widgets
 * 4. Detail fields display label: value for each property
 * 5. Empty state shows placeholder when no entity selected
 */
class MasterDetailFkTest {

    private val m3 = Material3Binding()
    private val web = WebDomBinding()

    // === Master-Detail Layout ===

    @Test
    fun `material3 masterDetail has two weighted boxes`() {
        val code = m3.masterDetail("  ListContent()", "  DetailContent()")
        assertThat(code).contains("Row")
        assertThat(code).contains("Modifier.weight(1f)")
        assertThat(code).contains("ListContent()")
        assertThat(code).contains("DetailContent()")
        assertThat(code).contains("// Master")
        assertThat(code).contains("// Detail")
    }

    @Test
    fun `webDom masterDetail uses flexbox row with gap`() {
        val code = web.masterDetail("  ListContent()", "  DetailContent()")
        assertThat(code).contains("FlexDirection.Row")
        assertThat(code).contains("gap")
        assertThat(code).contains("flex(1)")
        assertThat(code).contains("ListContent()")
        assertThat(code).contains("DetailContent()")
    }

    // === FK Navigation Links ===

    @Test
    fun `material3 fkLink generates TextButton with arrow`() {
        val code = m3.fkLink("Owner", "entity.ownerId", "Owner", "onOwnerClick()")
        assertThat(code).contains("TextButton")
        assertThat(code).contains("onOwnerClick()")
        assertThat(code).contains("→ Owner")
        assertThat(code).contains("entity.ownerId")
    }

    @Test
    fun `webDom fkLink generates anchor with cursor pointer`() {
        val code = web.fkLink("Category", "entity.categoryId", "Category", "navigateToCategory()")
        assertThat(code).contains("A(")
        assertThat(code).contains("cursor(\"pointer\")")
        assertThat(code).contains("color")
        assertThat(code).contains("→ Category")
        assertThat(code).contains("navigateToCategory()")
    }

    @Test
    fun `fkLink renders differently per target`() {
        val m3Code = m3.fkLink("Ref", "id", "Target", "nav()")
        val webCode = web.fkLink("Ref", "id", "Target", "nav()")
        assertThat(m3Code).contains("TextButton")
        assertThat(webCode).contains("A(")
        assertThat(m3Code).doesNotContain("A(")
        assertThat(webCode).doesNotContain("TextButton")
    }

    // === Detail Fields ===

    @Test
    fun `material3 detailField renders Text composable`() {
        val code = m3.detailField("Name", "entity.name")
        assertThat(code).contains("material3.Text")
        assertThat(code).contains("Name:")
        assertThat(code).contains("entity.name")
    }

    @Test
    fun `webDom detailField renders P element`() {
        val code = web.detailField("Email", "entity.email")
        assertThat(code).contains("P {")
        assertThat(code).contains("Email:")
        assertThat(code).contains("entity.email")
    }

    // === Submit + Add + Empty State ===

    @Test
    fun `material3 submitButton`() {
        val code = m3.submitButton("Save", "onSubmit(entity)")
        assertThat(code).contains("Button")
        assertThat(code).contains("\"Save\"")
    }

    @Test
    fun `webDom submitButton`() {
        val code = web.submitButton("Save", "onSubmit(entity)")
        assertThat(code).contains("Button(")
        assertThat(code).contains("onClick")
    }

    @Test
    fun `material3 emptyState`() {
        val code = m3.emptyState("Select a pet")
        assertThat(code).contains("Text(\"Select a pet\")")
    }

    @Test
    fun `webDom emptyState has gray color`() {
        val code = web.emptyState("No selection")
        assertThat(code).contains("gray")
        assertThat(code).contains("No selection")
    }

    @Test
    fun `material3 addButton`() {
        val code = m3.addButton("+ Add Pet", "onAdd()")
        assertThat(code).contains("TextButton")
        assertThat(code).contains("+ Add Pet")
    }

    @Test
    fun `webDom addButton`() {
        val code = web.addButton("+ New", "onCreate()")
        assertThat(code).contains("Button(")
        assertThat(code).contains("+ New")
    }

    // === Full Master-Detail with FK scenario ===

    @Test
    fun `material3 full pet-owner master-detail`() {
        val master = m3.addButton("+ Add Pet", "onAdd()") + "\n" +
            m3.lazyColumn("items.size", "val item = items[index]\n        " + m3.listItem("item.name", "onSelect(item)"))
        val detail = m3.detailField("Name", "selected.name") + "\n" +
            m3.detailField("Status", "selected.status") + "\n" +
            m3.fkLink("Owner", "selected.ownerId", "Owner", "navigateToOwner(selected.ownerId)")
        val fullCode = m3.masterDetail(master, detail)

        assertThat(fullCode).contains("Row")
        assertThat(fullCode).contains("+ Add Pet")
        assertThat(fullCode).contains("LazyColumn")
        assertThat(fullCode).contains("Name:")
        assertThat(fullCode).contains("→ Owner")
        assertThat(fullCode).contains("navigateToOwner")
    }

    @Test
    fun `webDom full order-product master-detail`() {
        val master = web.addButton("+ New Order", "createOrder()") + "\n" +
            web.lazyColumn("orders.size", web.listItem("orders[index].id.toString()", "select(orders[index])"))
        val detail = web.detailField("Order ID", "selected.id") + "\n" +
            web.detailField("Quantity", "selected.quantity") + "\n" +
            web.fkLink("Product", "selected.productId", "Product", "goToProduct(selected.productId)")
        val fullCode = web.masterDetail(master, detail)

        assertThat(fullCode).contains("FlexDirection.Row")
        assertThat(fullCode).contains("+ New Order")
        assertThat(fullCode).contains("Ul {")
        assertThat(fullCode).contains("Order ID:")
        assertThat(fullCode).contains("→ Product")
        assertThat(fullCode).contains("goToProduct")
    }
}
