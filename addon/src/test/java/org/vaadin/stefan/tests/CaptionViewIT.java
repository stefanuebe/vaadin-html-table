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

import org.junit.Assert;
import org.junit.Test;
import org.vaadin.stefan.AbstractTableViewTest;
import org.vaadin.stefan.table.elements.TableCaptionElement;
import org.vaadin.stefan.table.elements.TableElement;
import org.vaadin.stefan.views.CaptionView;

public class CaptionViewIT extends AbstractTableViewTest {

    public CaptionViewIT() {
        super(CaptionView.class);
    }

    @Test
    public void componentWorks() {
        TableElement table = getTable();

        assertOptionalPresent("tcaption", table::getOptionalCaption);

        TableCaptionElement caption = table.getCaption();
        Assert.assertEquals("Some caption", caption.getText());
    }
}
