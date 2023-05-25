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

import com.vaadin.flow.router.RouteAlias;
import org.vaadin.stefan.table.Table;
import org.vaadin.stefan.table.TableRow;

@RouteAlias(value = "", layout = MainLayout.class)
public class SimpleTableView extends AbstractTableView {

    @Override
    protected void createContent(Table table) {
        TableRow headerRow = table.addRow();
        headerRow.addHeaderCell().setText("Hello");
        headerRow.addHeaderCell().setText("World");

        TableRow detailsRow = table.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");

    }
}
