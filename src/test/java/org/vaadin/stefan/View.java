package org.vaadin.stefan;

import org.vaadin.stefan.table.Table;
import org.vaadin.stefan.table.TableCell;
import org.vaadin.stefan.table.TableColumn;
import org.vaadin.stefan.table.TableColumnGroup;
import org.vaadin.stefan.table.TableHead;
import org.vaadin.stefan.table.TableHeaderCell;
import org.vaadin.stefan.table.TableRow;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;

@Route("")
public class View extends Div {

    public View() {
        buildSimpleTable();
        buildSimpleTableWithCaption();
        buildSimpleTableWithSpans();
        buildStructuredTable();
        buildStructuredTableWithColGroups();
    }

    private void buildSimpleTable() {
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


    private void buildSimpleTableWithCaption() {
        Table table = new Table();

        TableRow detailsRow = table.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");

        table.getCaption().setText("Some caption");

        add(table);
    }

    private void buildSimpleTableWithSpans() {
        Table table = new Table();
        table.setWidth("500px");

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
    }

    private void buildStructuredTable() {
        Table table = new Table();

        TableRow detailsRow = table.getBody().addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        add(table);

        TableHead head = table.getHead();
        TableRow headerRow = head.addRow();
        headerRow.addHeaderCell().setText("Hello");
        headerRow.addHeaderCell().setText("World");

        table.getCaption().setText("Using thead and tbody");

        add(table);
    }


    private void buildStructuredTableWithColGroups() {
        Table table = new Table();
        table.setWidth("500px");

        TableHead head = table.getHead();
        TableRow headerRow = head.addRow();
        headerRow.addHeaderCell().setText("Hello");
        headerRow.addHeaderCell().setText("World with Style");
        headerRow.addHeaderCell().setText("Hello");
        headerRow.addHeaderCell().setText("World");

        TableRow detailsRow = table.getBody().addRow();
        detailsRow.addDataCell().setText("Hello");
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
    }
}
