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

import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;
import org.openqa.selenium.NoSuchElementException;

import java.util.List;
import java.util.Optional;

/**
 * @author Stefan Uebe
 */
@Element("table")
public class TableElement extends TestBenchElement implements HasTableRowsElement {

    public TableHeadElement getHead() {
        return $(TableHeadElement.class).first();
    }

    public TableBodyElement getBody() {
        return $(TableBodyElement.class).first();
    }

    public List<TableBodyElement> getBodies() {
        return $(TableBodyElement.class).all();
    }

    public TableFootElement getFoot() {
        return $(TableFootElement.class).first();
    }
    public TableCaptionElement getCaption() {
        return $(TableCaptionElement.class).first();
    }

    public TableColumnGroupElement getColumnGroup() {
        return $(TableColumnGroupElement.class).first();
    }

    public Optional<TableHeadElement> getOptionalHead() {
        try {
            return Optional.of(getHead());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<TableBodyElement> getOptionalBody() {
        try {
            return Optional.of(getBody());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<TableFootElement> getOptionalFoot() {
        try {
            return Optional.of(getFoot());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }
    public Optional<TableCaptionElement> getOptionalCaption() {
        try {
            return Optional.of(getCaption());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

    public Optional<TableColumnGroupElement> getOptionalColumnGroup() {
        try {
            return Optional.of(getColumnGroup());
        } catch (NoSuchElementException e) {
            return Optional.empty();
        }
    }

}
