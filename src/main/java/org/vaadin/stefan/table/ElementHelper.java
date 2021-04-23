package org.vaadin.stefan.table;

import java.util.Arrays;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.dom.Element;

/**
 * @author Stefan Uebe
 */
public final class ElementHelper {
    private ElementHelper() {
        // utility class
    }

    public static Element[] asElements(Component... components) {
        return Arrays.stream(components).map(Component::getElement).toArray(Element[]::new);
    }
}
