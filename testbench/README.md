# HTML Table Testbench Elements

A set of Testbench Elements for the **HTML Table** addon for custom Vaadin Testbench UI tests.

@see https://vaadin.com/directory/component/html-table.

## Example test
Two example files on how a test with these Testbench elements could look like

SimpleTestView.java
```
@Route("simple")
public class SimpleTableView extends Div {

    public SimpleTableView() {
        Table table = new Table();
        table.setWidth("500px");

        TableRow headerRow = table.addRow();
        headerRow.addHeaderCell().setText("Hello");
        headerRow.addHeaderCell().setText("World");

        TableRow detailsRow = table.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");

        add(table);
    }
}
```

SimpleTestViewIT.java
```
public class SimpleTableViewIT extends TestBenchTestCase {

    // ... test setup and init

    @Test
    public void componentWorks() {
        TableElement table = getTable();

        Assert.assertFalse("caption" + " must not be present", table.getOptionalCaption().isPresent());
        Assert.assertFalse("colgroup" + " must not be present", table.getOptionalColumn().isPresent());
        Assert.assertFalse("thead" + " must not be present", table.getOptionalHead().isPresent());
        Assert.assertFalse("tbody" + " must not be present", table.getOptionalBody().isPresent());
        Assert.assertFalse("tfoot" + " must not be present", table.getOptionalFoot().isPresent());

        List<TableRowElement> rows = table.getRows();
        Assert.assertEquals(2, rows.size());

        TableRowElement row = rows.get(0);
        List<TableHeaderCellElement> headerCells = row.getHeaderCells();
        List<TableDataCellElement> dataCells = row.getDataCells();
        Assert.assertEquals(2, headerCells.size());
        Assert.assertEquals(0, dataCells.size());

        Assert.assertEquals("Hello", headerCells.get(0).getText());
        Assert.assertEquals("World", headerCells.get(1).getText());

        row = rows.get(1);
        headerCells = row.getHeaderCells();
        dataCells = row.getDataCells();
        Assert.assertEquals(0, headerCells.size());
        Assert.assertEquals(2, dataCells.size());

        Assert.assertEquals("Hello", dataCells.get(0).getText());
        Assert.assertEquals("World", dataCells.get(1).getText());
    }
    
}
```