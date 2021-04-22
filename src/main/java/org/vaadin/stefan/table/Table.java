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
package org.vaadin.stefan.table;

import java.util.Arrays;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.dom.Element;

/**
 * Represents the html table element ({@code <&lt;>table>}). Can contain
 * <li>
 *     <ul>a caption</ul>
 *     <ul>a column group</ul>
 *     <ul>a thead</ul>
 *     <ul>a tfoot</ul>
 *     <ul>a tbody or a set of table rows</ul>
 * </li>
 * <p/>
 * Subelements except for rows are created when calling the respective getter, for instance {@link #getHead()}. Since
 * {@code <table>} expects a certain order of elements, this class takes care of positioning them in the correct
 * order (e.g. head {@code <thead>}, then {@code <tbody>} and then {@code <tfoot>}, etc).
 * <p/>
 * Also a table must not contain rows as direct childs, when having a {@code <tbody>}. Therefore, all previously to the
 * table assigned rows are automatically assigned to the {@code <tbody>}, when {@link #getBody()} is called.
 *
 * @see TableCaption
 * @see TableColumnGroup
 * @see TableRow
 * @see TableHead
 * @see TableBody
 * @see TableFoot
 *
 * @author Stefan Uebe
 */
@Tag("table")
public class Table extends HtmlComponent implements TableRowContainer {

    private TableCaption caption;

    private TableColumnGroup columnGroup;

    private TableHead head;
    private TableBody body;
    private TableFoot foot;

    /**
     * Returns the {@link TableCaption} for this instance. Creates a new instance on the first call.
     * @return caption
     */
    public TableCaption getCaption() {
        if (caption == null) {
            caption = insertIndexedChild(new TableCaption());
        }

        return caption;
    }

    /**
     * Returns the {@link TableColumnGroup} for this instance. Creates a new instance on the first call.
     * @return column group
     */
    public TableColumnGroup getColumnGroup() {
        if (columnGroup == null) {
            columnGroup = insertIndexedChild(new TableColumnGroup(), caption);
        }

        return columnGroup;
    }

    /**
     * Returns the {@link TableHead} for this instance. Creates a new instance on the first call.
     * @return table head
     */
    public TableHead getHead() {
        if (head == null) {
            head = insertIndexedChild(new TableHead(), caption, columnGroup);
        }

        return head;
    }

    /**
     * Returns the {@link TableBody} for this instance. Creates a new instance on the first call.
     * <p/>
     * When rows have been assigned to this table instance before calling this method, these rows will
     * be reassigned to the body to prevent creating an invalid dom structure.
     * <p/>
     * Any subsequent calls to this table's {@link #addRows(TableRow...)} method will be delegated to the
     * body's one.
     * @return table body
     */
    public TableBody getBody() {
        if (body == null) {
            body = insertIndexedChild(new TableBody(), caption, columnGroup, head);
            body.addRows(streamRows().toArray(TableRow[]::new));
        }

        return body;
    }

    /**
     * Returns the {@link TableFoot} for this instance. Creates a new instance on the first call.
     * @return foot
     */
    public TableFoot getFoot() {
        if (foot == null) {
            foot = insertIndexedChild(new TableFoot(), caption, columnGroup, head, body);
        }

        return foot;
    }

    /**
     * Calculates an 0-based index based on the given components. Each non null component will increase
     * the index by 1. That means, passing (component1, component2, null, component3) will return an index
     * of 3. Passing nothing returns 0.
     * @param predecessors predecessors (null values allowed)
     * @return index based on the given non null components.
     */
    private int calculateIndex(Component... predecessors) {
        int index = 0;
        for (Component predecessor : predecessors) {
            if (predecessor != null) {
                index++;
            }
        }
        return index;
    }

    /**
     * Adds a child to this table with an index based on the given predecessors. Uses {@link #calculateIndex(Component...)}.
     * @param childToAdd child to add
     * @param predecessors predecessors (null values allowed)
     * @param <T> type of child to add
     * @return the parameter childToAdd (for subsequent calls)
     */
    private <T extends Component> T insertIndexedChild(T childToAdd, Component... predecessors) {
        int index = calculateIndex(predecessors);
        getElement().insertChild(index, childToAdd.getElement());
        return childToAdd;
    }

    /**
     * Adds the given list of rows.
     * <p/>
     * When this table has a body, it will automatically delegate the call to the body's add method.
     * @param rows rows
     */
    @Override
    public void addRows(TableRow... rows) {
        if (body != null) {
            body.addRows(rows);
        } else {
            getElement().appendChild(Arrays.stream(rows).map(Component::getElement).toArray(Element[]::new));
        }
    }
}
