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
package org.vaadin.stefan.table.elements;

import java.util.List;

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

/**
 * @author Stefan Uebe
 */
@Element("tr")
public class TableRowElement extends TestBenchElement {
    public List<TableCellElement> getCells() {
        return $(TableCellElement.class).all();
    }

    public List<TableDataCellElement> getDataCells() {
        return $(TableDataCellElement.class).all();
    }

    public List<TableHeaderCellElement> getHeaderCells() {
        return $(TableHeaderCellElement.class).all();
    }

}
