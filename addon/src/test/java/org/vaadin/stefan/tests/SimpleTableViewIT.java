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
package org.vaadin.stefan.tests;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.vaadin.stefan.AbstractTableViewTest;
import org.vaadin.stefan.table.elements.TableDataCellElement;
import org.vaadin.stefan.table.elements.TableElement;
import org.vaadin.stefan.table.elements.TableHeaderCellElement;
import org.vaadin.stefan.table.elements.TableRowElement;
import org.vaadin.stefan.views.SimpleTableView;

public class SimpleTableViewIT extends AbstractTableViewTest {

    public SimpleTableViewIT() {
        super(SimpleTableView.class);
    }

    @Test
    public void componentWorks() {
        TableElement table = getTable();

        assertOptionalNotPresent("caption", table::getOptionalCaption);
        assertOptionalNotPresent("colgroup", table::getOptionalColumnGroup);
        assertOptionalNotPresent("thead", table::getOptionalHead);
        assertOptionalNotPresent("tbody", table::getOptionalBody);
        assertOptionalNotPresent("tfoot", table::getOptionalFoot);

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
