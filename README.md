# HTML Table

Vaadin Flow integration of the html Table elements.

## Description

This addon provides a low-level api to work with a html table and related 
elements. It allows you to create a simple table consisting of rows and cells
or to use a more structured approach by defining columns, a head, body and foot.

## Features
* support of adding table rows, header and data cells as direct children, or
* structuring the table by using tbody, thead and tfoot
* takes care of the correct order of sub elements of the table (e.g. thead before tbody, etc.)
* rows added to the table are automatically transferred to the tbody, when created
* support for a table caption element
* support for column groups and columns
* table cells can define their col- and rowspan
* table header cells can get a scope assigned
* convenient api for easy adding or removing sub elements

## Dedicated Testbench elements
If you want to create Testbench tests testing table elements, you can reuse this
predefined set of elements: https://vaadin.com/directory/component/html-table-testbench-elements.

# Exapmles
## Creating a simple table
```
Table table = new Table();

TableRow headerRow = table.addRow();
headerRow.addHeaderCell().setText("Hello");
headerRow.addHeaderCell().setText("World");

TableRow detailsRow = table.addRow();
detailsRow.addDataCell().setText("Hello");
detailsRow.addDataCell().setText("World");
```

## Applying colspan and rowspan to cells
```
Table table = new Table();

TableRow headerRow = table.addRow();
TableCell cell = headerRow.addHeaderCell();
cell.setText("Hello world, it's me.");
cell.setColSpan(3);
cell.getStyle().set("background-color", "#fdd");

TableRow detailsRow = table.addRow();
detailsRow.addDataCell().setText("Hello");
detailsRow.addDataCell().setText("World");

cell = detailsRow.addDataCell();
cell.setText("It's me.");
cell.setRowSpan(2);
cell.getStyle().set("background-color", "#dfd");

detailsRow = table.addRow();
cell = detailsRow.addDataCell();
cell.setText("Hello");
cell.setColSpan(2);
cell.getStyle().set("background-color", "#ddf");

table.getCaption().setText("Using col- and rowspan");

add(table);
```

## Using a caption element
```
Table table = new Table();

table.getCaption().setText("Some caption"); 

// caption also supports components
table.getCaption().add(new Image(...));
```

## Structuring the table
```
// Table takes care to order the elements on the client side as required by html specifications

Table table = new Table();

TableRow detailsRow = table.getBody().addRow();
detailsRow.addDataCell().setText("Hello");
detailsRow.addDataCell().setText("World");
add(table);

TableHead head = table.getHead(); // will be put before tbody in the client side dom
TableRow headerRow = head.addRow();
headerRow.addHeaderCell().setText("Hello");
headerRow.addHeaderCell().setText("World");

table.getCaption().setText("Using thead and tbody"); // will be put before thead in the client side dom

add(table);
```

## Using column groups
```
Table table = new Table();

TableHead head = table.getHead();
TableRow headerRow = head.addRow();
headerRow.addHeaderCell().setText("Hello with Style");
headerRow.addHeaderCell().setText("World with Style");
headerRow.addHeaderCell().setText("Hello");
headerRow.addHeaderCell().setText("World");

TableRow detailsRow = table.getBody().addRow();
detailsRow.addDataCell().setText("Hello with Style");
detailsRow.addDataCell().setText("World with Style");
detailsRow.addDataCell().setText("Hello");
detailsRow.addDataCell().setText("World");

TableColumnGroup columnGroup = table.getColumnGroup();
TableColumn column = columnGroup.addColumn();
column.addClassName("some");
column.setSpan(2);

headerRow.streamHeaderCells().forEach(c -> c.setScope(TableHeaderCell.SCOPE_COLUMN));

table.getCaption().setText("Using colgroups, thead and tbody");

add(table);
```

## Integrating Vaadin components
```
Table table = new Table();

TableRow headerRow = table.addRow();
headerRow.addHeaderCell().setText("Name");
headerRow.addHeaderCell().setText("Age");

for (int i = 0; i < 10; i++) {
    TextField textField = new TextField();
    textField.setValue("Some user " + i );

    NumberField numberField = new NumberField();
    numberField.setValue((double) (20 + i));

    TableRow detailsRow = table.addRow();
    detailsRow.addDataCell().add(textField);
    detailsRow.addDataCell().add(numberField);
}

add(table);
```

## Changing the content
```
// The table and its content is modifiable as any other Vaadin component

Table table = new Table();
table.setWidth("500px");

TableRow headerRow = table.addRow();
headerRow.addHeaderCell().setText("Hello");
headerRow.addHeaderCell().setText("World");

TableRow detailsRow = table.addRow();
detailsRow.addDataCell().setText("Hello");
detailsRow.addDataCell().setText("World");

add(table, new Button("Change cell content", event -> {
    table.getRow(1)
            .flatMap(row -> row.getCell(1))
            .ifPresent(cell -> cell.setText("You :)"));
}));
```

## Creating a Testbench testcase
```
// Needs the html-table-testbench-elements
// @see https://vaadin.com/directory/component/html-table-testbench-elements

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
