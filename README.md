# HTML Table

Vaadin 14 Java integration of the html Table elements.

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

## Using a caption element
```
Table table = new Table();

table.getCaption().setText("Some caption"); 

// caption also supports components
table.getCaption().add(new Image(...));
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
