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
package org.vaadin.addons.stefan.gridscroller;

import org.vaadin.stefan.table.Table;
import org.vaadin.stefan.table.TableCell;
import org.vaadin.stefan.table.TableRow;

public class SpansView extends AbstractTableView {


    @Override
    protected void createContent(Table table) {
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
    }
}
