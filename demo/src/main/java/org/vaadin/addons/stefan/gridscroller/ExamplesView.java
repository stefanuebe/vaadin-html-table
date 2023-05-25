//package org.vaadin.addons.stefan.gridscroller;
//
//import org.vaadin.stefan.table.Table;
//import org.vaadin.stefan.table.TableCell;
//import org.vaadin.stefan.table.TableColumn;
//import org.vaadin.stefan.table.TableColumnGroup;
//import org.vaadin.stefan.table.TableHead;
//import org.vaadin.stefan.table.TableHeaderCell;
//import org.vaadin.stefan.table.TableRow;
//
//import com.vaadin.flow.component.button.Button;
//import com.vaadin.flow.component.button.ButtonVariant;
//import com.vaadin.flow.component.datepicker.DatePicker;
//import com.vaadin.flow.component.html.Div;
//import com.vaadin.flow.component.textfield.NumberField;
//import com.vaadin.flow.component.textfield.TextArea;
//import com.vaadin.flow.component.textfield.TextField;
//import com.vaadin.flow.router.Route;
//
//@Route("")
//public class ExamplesView extends Div {
//
//    public ExamplesView() {
//        buildSimpleTable();
//        buildSimpleTableWithCaption();
//        buildSimpleTableWithSpans();
//        buildTableWithVaadinComponents();
//        buildStructuredTable();
//        buildStructuredTableWithColGroups();
//        buildTableWithChange();
//        buildVaadin1();
//        buildVaadin2();
//    }
//
//    private void buildSimpleTable() {
//        Table table = new Table();
//        table.setWidth("500px");
//
//        TableRow headerRow = table.addRow();
//        headerRow.addHeaderCell().setText("Hello");
//        headerRow.addHeaderCell().setText("World");
//
//        TableRow detailsRow = table.addRow();
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World");
//
//        add(table);
//    }
//
//
//    private void buildSimpleTableWithCaption() {
//        Table table = new Table();
//
//        TableRow detailsRow = table.addRow();
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World");
//
//        table.getCaption().setText("Some caption");
//
//        add(table);
//    }
//
//    private void buildSimpleTableWithSpans() {
//        Table table = new Table();
//        table.setWidth("500px");
//
//        TableRow headerRow = table.addRow();
//        TableCell cell = headerRow.addHeaderCell();
//        cell.setText("Hello world, it's me.");
//        cell.setColSpan(3);
//        cell.getStyle().set("background-color", "#fdd");
//
//        TableRow detailsRow = table.addRow();
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World");
//
//        cell = detailsRow.addDataCell();
//        cell.setText("It's me.");
//        cell.setRowSpan(2);
//        cell.getStyle().set("background-color", "#dfd");
//
//        detailsRow = table.addRow();
//        cell = detailsRow.addDataCell();
//        cell.setText("Hello");
//        cell.setColSpan(2);
//        cell.getStyle().set("background-color", "#ddf");
//
//        table.getCaption().setText("Using col- and rowspan");
//
//        add(table);
//    }
//
//    private void buildTableWithVaadinComponents() {
//        Table table = new Table();
//        table.setWidth("500px");
//
//        TableRow headerRow = table.addRow();
//        headerRow.addHeaderCell().setText("Name");
//        headerRow.addHeaderCell().setText("Age");
//
//        for (int i = 0; i < 10; i++) {
//            TextField textField = new TextField();
//            textField.setValue("Some user " + i );
//
//            NumberField numberField = new NumberField();
//            numberField.setValue((double) (20 + i));
//
//            TableRow detailsRow = table.addRow();
//            detailsRow.addDataCell().add(textField);
//            detailsRow.addDataCell().add(numberField);
//        }
//
//        add(table);
//    }
//
//    private void buildStructuredTable() {
//        Table table = new Table();
//
//        TableRow detailsRow = table.getBody().addRow();
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World");
//        add(table);
//
//        TableHead head = table.getHead();
//        TableRow headerRow = head.addRow();
//        headerRow.addHeaderCell().setText("Hello");
//        headerRow.addHeaderCell().setText("World");
//
//        table.getCaption().setText("Using thead and tbody");
//
//        add(table);
//    }
//
//
//    private void buildStructuredTableWithColGroups() {
//        Table table = new Table();
//        table.setWidth("500px");
//
//        TableHead head = table.getHead();
//        TableRow headerRow = head.addRow();
//        headerRow.addHeaderCell().setText("Hello");
//        headerRow.addHeaderCell().setText("World with Style");
//        headerRow.addHeaderCell().setText("Hello");
//        headerRow.addHeaderCell().setText("World");
//
//        TableRow detailsRow = table.getBody().addRow();
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World with Style");
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World");
//
//        TableColumnGroup columnGroup = table.getColumnGroup();
//        TableColumn column = columnGroup.addColumn();
//        column.addClassName("some");
//        column.setSpan(2);
//
//        headerRow.streamHeaderCells().forEach(c -> c.setScope(TableHeaderCell.SCOPE_COLUMN));
//
//        table.getCaption().setText("Using colgroups, thead and tbody");
//
//        add(table);
//    }
//
//    private void buildTableWithChange() {
//        Table table = new Table();
//        table.setWidth("500px");
//
//        TableRow headerRow = table.addRow();
//        headerRow.addHeaderCell().setText("Hello");
//        headerRow.addHeaderCell().setText("World");
//
//        TableRow detailsRow = table.addRow();
//        detailsRow.addDataCell().setText("Hello");
//        detailsRow.addDataCell().setText("World");
//
//        add(table, new Button("Change cell content", event -> {
//            table.getRow(1)
//                    .flatMap(row -> row.getCell(1))
//                    .ifPresent(cell -> cell.setText("You :)"));
//        }));
//    }
//
//    private void buildVaadin1() {
//        Table table = new Table();
//
//        TableRow headerRow = table.addRow();
//        headerRow.addHeaderCell().setText("Name");
//        headerRow.addHeaderCell().setText("Age");
//
//        for (int i = 0; i < 10; i++) {
//            TextField textField = new TextField();
//            textField.setValue("Some user " + i );
//
//            com.vaadin.flow.component.textfield.NumberField numberField = new com.vaadin.flow.component.textfield.NumberField();
//            numberField.setValue((double) (20 + i));
//
//            TableRow detailsRow = table.addRow();
//            detailsRow.addDataCell().add(textField);
//            detailsRow.addDataCell().add(numberField);
//        }
//
//        add(table);
//    }
//
//
//    private void buildVaadin2() {
//        TableRow row;
//        TableCell cell;
//
//        Table table = new Table();
//        table.setWidth("500px");
//
//        row = table.addRow();
//        TextField firstName = new TextField("First name");
//        TextField lastName = new TextField("Last name");
//        firstName.setWidthFull();
//        lastName.setWidthFull();
//        row.addDataCell().add(firstName);
//        row.addDataCell().add(lastName);
//
//        row = table.addRow();
//        cell = row.addDataCell();
//        TextField address = new TextField("Address");
//        address.setWidthFull();
//        cell.add(address);
//        cell.setColSpan(2);
//
//        row = table.addRow();
//        DatePicker dateOfBirth = new DatePicker("Date of Birth");
//        dateOfBirth.setWidthFull();
//        row.addDataCell().add(dateOfBirth);
//        cell = row.addDataCell();
//        TextArea area = new TextArea("Comments");
//        area.setSizeFull();
//
//        cell.add(area);
//        cell.getStyle().set("vertical-align", "top");
//        cell.setRowSpan(2);
//
//        row = table.addRow();
//        TextField gender = new TextField("Gender");
//        gender.setWidthFull();
//        row.addDataCell().add(gender);
//
//        row = table.addRow();
//        cell = row.addDataCell();
//        cell.setColSpan(2);
//        Button save = new Button("Save");
//        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
//        cell.add(save, new Button("Cancel"), new Button("Send e-mail"));
//
//        add(table);
//    }
//}
