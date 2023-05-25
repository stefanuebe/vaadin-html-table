package org.vaadin.addons.stefan.gridscroller;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.RouterLink;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;


@CssImport("./styles/styles.css")
public class MainLayout extends AppLayout implements AfterNavigationObserver {

    // cache created menu items for updating the selection on navigation
    private final Map<Class<?>, RouterLink> menuItems = new HashMap<>();

    private RouterLink currentView;

    public MainLayout() {
        DrawerToggle toggle = new DrawerToggle();

        H1 title = new H1("MyApp");
        title.getStyle().set("font-size", "var(--lumo-font-size-l)")
                .set("margin", "0");

        createMenuItems();
        addToNavbar(toggle, title);
    }

    private void createMenuItems() {
        addToDrawer(
                createMenuItem(SimpleTableView.class),
                createMenuItem(CaptionView.class),
                createMenuItem(SpansView.class),
                createMenuItem(StructuredTableView.class),
                createMenuItem(StructuredTableMultipleBodiesView.class),
                createMenuItem(ChangeEventView.class),
                createMenuItem(VaadinComponentsTableView.class),
                createMenuItem(VaadinComponentsFormView.class)
        );

    }

    private RouterLink createMenuItem(Class<? extends Component> target) {
        RouterLink link = new RouterLink(target);

        String name = getViewName(target);

        link.setText(name);
        menuItems.put(target, link);

        return link;
    }

    private static String getViewName(Class<? extends Component> target) {
        String name = target.getSimpleName();
        if (name.endsWith("View")) {
            name = name.substring(0, name.length() - 4);
            name = String.join(" ", StringUtils.splitByCharacterTypeCamelCase(name));
        }
        return name;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {
        super.afterNavigation();

        if (currentView != null) {
            currentView.getStyle().remove("font-weight");
        }

        Class<? extends Component> viewType = getContent().getClass();
        currentView = menuItems.get(viewType);
        currentView.getStyle().set("font-weight", "bold");
        UI.getCurrent().getPage().setTitle(getViewName(viewType));
    }
}