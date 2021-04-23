/*
 * Copyright 2021 by Stefan Uebe
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.vaadin.stefan.views;

import org.vaadin.stefan.table.Table;
import org.vaadin.stefan.table.TableCell;
import org.vaadin.stefan.table.TableColumn;
import org.vaadin.stefan.table.TableColumnGroup;
import org.vaadin.stefan.table.TableHead;
import org.vaadin.stefan.table.TableHeaderCell;
import org.vaadin.stefan.table.TableRow;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;

@Route("vaadin-form")
public class VaadinComponentsFormView extends Div {

    public VaadinComponentsFormView() {
        TableRow row;
        TableCell cell;

        Table table = new Table();
        table.setWidth("500px");

        row = table.addRow();
        TextField firstName = new TextField("First name");
        TextField lastName = new TextField("Last name");
        firstName.setWidthFull();
        lastName.setWidthFull();
        row.addDataCell().add(firstName);
        row.addDataCell().add(lastName);

        row = table.addRow();
        cell = row.addDataCell();
        TextField address = new TextField("Address");
        address.setWidthFull();
        cell.add(address);
        cell.setColSpan(2);

        row = table.addRow();
        DatePicker dateOfBirth = new DatePicker("Date of Birth");
        dateOfBirth.setWidthFull();
        row.addDataCell().add(dateOfBirth);
        cell = row.addDataCell();
        TextArea area = new TextArea("Comments");
        area.setSizeFull();

        cell.add(area);
        cell.getStyle().set("vertical-align", "top");
        cell.setRowSpan(2);

        row = table.addRow();
        TextField gender = new TextField("Gender");
        gender.setWidthFull();
        row.addDataCell().add(gender);

        row = table.addRow();
        cell = row.addDataCell();
        cell.setColSpan(2);
        Button save = new Button("Save");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        cell.add(save, new Button("Cancel"), new Button("Send e-mail"));

        add(table);
    }
}
