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
