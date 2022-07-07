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

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.table.*;

@Route("structured-bodies")
@CssImport("./styles/structured-bodies.css")
public class StructuredTableMultipleBodiesView extends Div {

    public StructuredTableMultipleBodiesView() {
        Table table = new Table();
        table.setWidth("500px");

        TableHead head = table.getHead();
        TableRow headerRow = head.addRow();
        headerRow.addHeaderCell().setText("Hello");
        headerRow.addHeaderCell().setText("World");
        headerRow.addHeaderCell().setText("This");
        headerRow.addHeaderCell().setText("is");
        headerRow.addHeaderCell().setText("the");
        headerRow.addHeaderCell().setText("header");

        TableFoot foot = table.getFoot();
        TableRow footRow = foot.addRow();
        footRow.addHeaderCell().setText("Hello");
        footRow.addHeaderCell().setText("World");
        footRow.addHeaderCell().setText("This");
        footRow.addHeaderCell().setText("is");
        footRow.addHeaderCell().setText("the");
        footRow.addHeaderCell().setText("footer");

        TableBody body = table.addBody();
        TableRow detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("ONE");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("ONE");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("ONE");

        body = table.addBody();
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("TWO");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("TWO");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("TWO");

        body = table.addBody();
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("THREE");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("THREE");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("THREE");

        body = table.addBody();
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("FOUR");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("FOUR");
        detailsRow = body.addRow();
        detailsRow.addDataCell().setText("Hello");
        detailsRow.addDataCell().setText("World");
        detailsRow.addDataCell().setText("This");
        detailsRow.addDataCell().setText("is");
        detailsRow.addDataCell().setText("body");
        detailsRow.addDataCell().setText("FOUR");

        TableColumnGroup columnGroup = table.getColumnGroup();
        TableColumn column = columnGroup.addColumn();
        column.addClassName("some");
        column.setSpan(2);

        footRow.streamHeaderCells().forEach(c -> c.setScope(TableHeaderCell.SCOPE_COLUMN));

        table.getCaption().setText("Using multiple tbodies");

        add(table);
    }

}
