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

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.Tag;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Represents the html table element ({@code <table>}). Can contain
 * <ul>
 *     <li>a caption</li>
 *     <li>a column group</li>
 *     <li>a thead</li>
 *     <li>a tfoot</li>
 *     <li>a tbody or a set of table rows</li>
 * </ul>
 * <br><br>
 * Sub elements except for rows are created when calling the respective getter, for instance {@link #getHead()}. Since
 * {@code <table>} expects a certain order of elements, this class takes care of positioning them in the correct
 * order (e.g. head {@code <thead>}, then {@code <tbody>} and then {@code <tfoot>}, etc).
 * <br><br>
 * Also a table must not contain rows as direct children, when having a {@code <tbody>}. Therefore, all previously to the
 * table assigned rows are automatically assigned to the {@code <tbody>}, when {@link #getBody()} is called.
 * <br><br>
 * Also all subsequent calls to any table row method are automatically delegated to the {@code <tbody>}.
 *
 * @author Stefan Uebe
 * @see TableCaption
 * @see TableColumnGroup
 * @see TableRow
 * @see TableHead
 * @see TableBody
 * @see TableFoot
 */
@Tag("table")
public class Table extends HtmlComponent implements TableRowContainer {

    private TableCaption caption;

    private TableColumnGroup columnGroup;

    private TableHead head;
    private final List<TableBody> bodies = new LinkedList<>();
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
     * Removes the head instance from this table.
     * <br><br>
     * Does <b>not</b> move any rows from the head to the table itself. That has
     * to be done manually.
     */
    public void removeHead() {
        if (head != null) {
            getElement().removeChild(head.getElement());
        }
    }

    /**
     * Removes the body instance of this table. If there are multiple body elements, it will remove the first
     * one (to align with {@link #getBody()}). Noop, if there is no body set to this table.
     * <br><br>
     * Does <b>not</b> move any rows from the bodies to the table itself. That has
     * to be done manually.
     */
    public void removeBody() {
        if (!bodies.isEmpty()) {
            removeBody(0);
        }
    }

    /**
     * Removes the body element with the given index.
     *
     * @param index index
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= getBodies().size())
     */
    public void removeBody(int index) {
        TableBody removed = bodies.remove(index);
        getElement().removeChild(removed.getElement());
    }

    /**
     * Removes all body instances from this table.
     * <br><br>
     * Does <b>not</b> move any rows from the bodies to the table itself. That has
     * to be done manually.
     */
    public void removeBodies() {
        bodies.forEach(body -> getElement().removeChild(body.getElement()));
        bodies.clear();
    }

    /**
     * Removes the foot instance from this table.
     * <br><br>
     * Does <b>not</b> move any rows from the foot to the table itself. That has
     * to be done manually.
     */
    public void removeFoot() {
        if (foot != null) {
            getElement().removeChild(foot.getElement());
        }
    }

    /**
     * Removes the caption instance from this table.
     */
    public void removeCaption() {
        if (caption != null) {
            getElement().removeChild(caption.getElement());
        }
    }

    /**
     * Removes the column group instance from this table.
     */
    public void removeColumnGroup() {
        if (columnGroup != null) {
            getElement().removeChild(columnGroup.getElement());
        }
    }

    /**
     * Returns the {@link TableBody} element for this instance (or the first one, if multiple body elements
     * have been assigned).
     * <br><br>
     * If this table had no body elements before, this method will transfer all rows of this table to the
     * created body element to prevent an invalid dom structure. Also any subsequent calls to this table's
     * {@link #addRows(TableRow...)} method will be delegated to that body element / the first body element, if
     * there are multiple.
     * @return table body
     */
    public TableBody getBody() {
        if (bodies.isEmpty()) {
            return addBody();
        }

        return bodies.get(0);
    }

    /**
     * Adds a new body element to this table.
     * <br><br>
     * If this table had no body elements before, this method will transfer all rows of this table to the
     * created body element to prevent an invalid dom structure. Also any subsequent calls to this table's
     * {@link #addRows(TableRow...)} method will be delegated to that body element / the first body element, if
     * there are multiple.
     * @return added body
     */
    public TableBody addBody() {
        TableBody body = new TableBody();
        addBodies(body);
        return body;
    }

    /**
     * Adds the given body elements to this table.
     * <br><br>
     * If this table had no body elements before, this method will transfer all rows of this table to the
     * first given body element to prevent an invalid dom structure. Also any subsequent calls to this table's
     * {@link #addRows(TableRow...)} method will be delegated to the first body's one.
     */
    public void addBodies(TableBody... tableBodies) {
        boolean noBodiesBefore = bodies.isEmpty();
        for (TableBody body : tableBodies) {
            insertIndexedChild(body, calculateInsertIndexedChildPredecessors());
            bodies.add(body);
        }

        if (noBodiesBefore && !bodies.isEmpty()) {
            transferTableRowsToBody(bodies.get(0));
        }
    }

    /**
     * Transfers all rows from this table instance to the given body.
     * @param body target body
     */
    private void transferTableRowsToBody(TableBody body) {
        body.addRows(streamRows().toArray(TableRow[]::new));
    }

    /**
     * Returns the body with the given index. If no bodies have been added yet and the parameter is 0,
     * this method will delegate the call to {@link #getBody()} instead and return the given value.
     * @param index body element index
     * @return body element
     * @throws IndexOutOfBoundsException – if the index is out of range (index < 0 || index >= getBodies().size())
     */
    public TableBody getBody(int index) {
        if (bodies.isEmpty() && index == 0) {
            return getBody();
        }

        return bodies.get(index);
    }

    /**
     * Returns a modifiable copy of the list of table bodies. Never null.
     * @return bodies
     */
    public List<TableBody> getBodies() {
        return new ArrayList<>(bodies);
    }

    /**
     * Returns the {@link TableFoot} for this instance. Creates a new instance on the first call.
     * @return foot
     */
    public TableFoot getFoot() {
        if (foot == null) {
            Component[] predecessors = calculateInsertIndexedChildPredecessors();
            foot = insertIndexedChild(new TableFoot(), predecessors);
        }

        return foot;
    }

    /**
     * Convenience method, that returns an array of all predecessor components for new body elements or the
     * footer. The array may contain null items.
     * @return array
     */
    private Component[] calculateInsertIndexedChildPredecessors() {
        return Stream.concat(Stream.of(caption, columnGroup, head), bodies.stream()).toArray(Component[]::new);
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
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @param rows rows
     */
    @Override
    public void addRows(TableRow... rows) {
        if (bodies.isEmpty()) {
            TableRowContainer.super.addRows(rows);
        } else {
            getBody().addRows(rows);
        }
    }

    /**
     * Inserts the given rows at the given index to this instance. Existing items will be placed after the inserted items.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @param rows rows
     */
    @Override
    public void insertRows(int index, TableRow... rows) {
        if (bodies.isEmpty()) {
            TableRowContainer.super.insertRows(index, rows);
        } else {
            getBody().insertRows(index, rows);
        }
    }

    /**
     * Replaces a single row instance to the given index in this container and replaces the existing row.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @param index index to set the new item to
     * @param row row
     */
    @Override
    public void replaceRow(int index, TableRow row) {
        if (bodies.isEmpty()) {
            TableRowContainer.super.replaceRow(index, row);
        } else {
            getBody().replaceRow(index, row);
        }
    }

    /**
     * Removes the given rows from this instance. Items, that are not child of this instance, will lead to an
     * exception.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @param rows rows to remove
     */
    @Override
    public void removeRows(TableRow... rows) {
        if (bodies.isEmpty()) {
            TableRowContainer.super.removeRows(rows);
        } else {
            getBody().removeRows(rows);
        }
    }

    /**
     * Returns the rows of this instance as a stream. Empty if no rows have been added yet.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @return rows as stream
     */
    @Override
    public Stream<TableRow> streamRows() {
        if (bodies.isEmpty()) {
            return TableRowContainer.super.streamRows();
        } else {
            return getBody().streamRows();
        }
    }

    /**
     * Adds a single row instance to this container. The created row is returned for further configuration.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @return the created row
     */
    @Override
    public TableRow addRow() {
        TableRow tableRow = new TableRow();
        addRows(tableRow);
        return tableRow;
    }

    /**
     * Adds multiple rows to this instance based on the given integer (must be greater than 0).
     * The created rows are returned as an array, that can be used for further configuration.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @param rows amount of rows to add
     * @return created row objects
     */
    @Override
    public TableRow[] addRows(int rows) {
        TableRow[] array = new TableRow[rows];
        for (int i = 0; i < rows; i++) {
            array[i] = new TableRow();
        }
        addRows(array);
        return array;
    }

    /**
     * Inserts a single row instance at the given index to this container. Existing items will be placed after the inserted items.
     * The created row is returned for further configuration.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     *
     * @return the created row
     */
    @Override
    public TableRow insertRow(int index) {
        TableRow row = new TableRow();
        insertRows(index, row);
        return row;
    }

    /**
     * Replaces a single row instance to the given index in this container and replaces the existing row.
     * <br><br>
     * This method has the same functionality as {@link #replaceRow(int, TableRow)}.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     *
     * @see #replaceRow(int, TableRow)
     * @param index index to set the new item to
     * @param row row
     */
    @Override
    public void setRow(int index, TableRow row) {
        replaceRow(index, row);
    }

    /**
     * Removes all rows.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     */
    @Override
    public void removeAllRows() {
        if (bodies.isEmpty()) {
            TableRowContainer.super.removeAllRows();
        } else {
            getBody().removeAllRows();
        }
    }

    /**
     * Removes the row with the given index. Noop, if no row has been found for that index.
     * <br><br>
     * When this table has a body, it will automatically delegate the call to the body's respective method. When
     * there are multiple body elements, it will be delegated to the first one.
     * @param index index to remove
     */
    @Override
    public void removeRow(int index) {
        getRow(index).ifPresent(this::removeRows);
    }


}
