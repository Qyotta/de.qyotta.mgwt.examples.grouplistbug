/*******************************************************************************
 * Qyotta UG (haftungsbeschränkt) (http://www.qyotta.de).
 * All rights reserved.
 * Copyright (c) 2012 Qyotta UG
 *******************************************************************************/
package de.qyotta.mgwt.examples.client;

import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.googlecode.mgwt.ui.client.widget.celllist.Cell;

class HeaderCell implements Cell<Header> {

   @Override
   public void render(final SafeHtmlBuilder safeHtmlBuilder, final Header model) {
      safeHtmlBuilder.appendEscaped(model.getName());
   }

   @Override
   public boolean canBeSelected(final Header model) {
      return false;
   }

}