package org.vaadin.addons.stefan.gridscroller;

import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.router.Route;
import org.vaadin.stefan.table.Table;

/**
 * @author Stefan Uebe
 */
@Route(layout = MainLayout.class)
public abstract class AbstractTableView extends Div {

    public AbstractTableView() {
        addClassName("view");

        Table table = new Table();
        add(table);
        createContent(table);
    }



    protected abstract void createContent(Table table);
}
