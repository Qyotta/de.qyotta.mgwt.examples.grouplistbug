/*
 * Copyright 2010 Daniel Kurka
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package de.qyotta.mgwt.examples.client;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartEvent;
import com.googlecode.mgwt.dom.client.event.touch.TouchStartHandler;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.widget.Button;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.StandardCellGroup;
import com.googlecode.mgwt.ui.client.widget.HeaderList;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.ContactsTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.RootTabPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.TabPanel;

/**
 * @author Daniel Kurka
 * 
 */
public class MgwtAppEntryPoint implements EntryPoint {

   @Override
   public void onModuleLoad() {
      MGWT.applySettings(MGWTSettings.getAppSetting());

      // listOnly();
      rootTabPanel();
      // tabPanel();
      // listOnlyWithFixedHeight();
      // listOnlyWithButtons();
   }

   private void listOnly() {
      final AnimationHelper animationHelper = new AnimationHelper();
      RootPanel.get().add(animationHelper);

      final GroupingCellList<Header, Content> groupingCellList = new GroupingCellList<Header, Content>(new ContentCell(), new HeaderCell());
      final HeaderList<Header, Content> headerList = new HeaderList<Header, Content>(groupingCellList);
      headerList.render(buildList(6));

      final LayoutPanel layoutPanel = new LayoutPanel();
      layoutPanel.add(headerList);

      animationHelper.goTo(layoutPanel, Animation.SLIDE);
   }

   private void rootTabPanel() {
      final RootTabPanel myRootTabs = new RootTabPanel();
      myRootTabs.add(new ContactsTabBarButton());

      RootPanel.get().add(myRootTabs);

      final LayoutPanel layoutPanel = new LayoutPanel();
      final GroupingCellList<Header, Content> groupingCellList = new GroupingCellList<Header, Content>(new ContentCell(), new HeaderCell());
      final HeaderList<Header, Content> headerList = new HeaderList<Header, Content>(groupingCellList);
      layoutPanel.add(headerList);
      headerList.render(buildList(6));

      myRootTabs.getAnimatableDisplay().setFirstWidget(layoutPanel);
      myRootTabs.getAnimatableDisplay().animate(Animation.SLIDE, true, null);
   }

   private void tabPanel() {
      final LayoutPanel layoutPanel = new LayoutPanel();

      final GroupingCellList<Header, Content> groupingCellList = new GroupingCellList<Header, Content>(new ContentCell(), new HeaderCell());
      final HeaderList<Header, Content> headerList = new HeaderList<Header, Content>(groupingCellList);
      layoutPanel.add(headerList);

      final TabPanel tabPanel = new TabPanel();
      tabPanel.add(new ContactsTabBarButton(), headerList);
      RootPanel.get().add(tabPanel);

      headerList.render(buildList(6));
   }

   private void listOnlyWithFixedHeight() {
      final AnimationHelper animationHelper = new AnimationHelper();
      RootPanel.get().add(animationHelper);

      final GroupingCellList<Header, Content> groupingCellList = new GroupingCellList<Header, Content>(new ContentCell(), new HeaderCell());
      final HeaderList<Header, Content> headerList = new HeaderList<Header, Content>(groupingCellList);
      headerList.render(buildList(6));

      final LayoutPanel layoutPanel = new LayoutPanel();
      layoutPanel.setHeight("90%");
      layoutPanel.add(headerList);

      animationHelper.goTo(layoutPanel, Animation.SLIDE);
   }

   private void listOnlyWithButtons() {
      final AnimationHelper animationHelper = new AnimationHelper();
      RootPanel.get().add(animationHelper);

      final FlowPanel buttonContainer = new FlowPanel();
      buttonContainer.setHeight("48px");

      buttonContainer.getElement().getStyle().setProperty("display", "-webkit-box");
      buttonContainer.getElement().getStyle().setProperty("WebkitBoxOrient", "horizontal");
      buttonContainer.getElement().getStyle().setProperty("borderTop", "1px solid black");
      buttonContainer.getElement().getStyle()
            .setProperty("backgroundImage", "-webkit-gradient(linear, left top, left bottom, from(#2E2E2E), color-stop(50%,#161616), color-stop(51%, black), to(black))");

      final ContactsTabBarButton w2 = new ContactsTabBarButton();
      w2.getElement().getStyle().setProperty("WebkitBoxFlex", "1");
      buttonContainer.add(w2);

      final GroupingCellList<Header, Content> groupingCellList = new GroupingCellList<Header, Content>(new ContentCell(), new HeaderCell());
      final HeaderList<Header, Content> headerList = new HeaderList<Header, Content>(groupingCellList);
      headerList.render(buildList(6));

      final LayoutPanel contactsPanel = new LayoutPanel();
      contactsPanel.add(headerList);

      contactsPanel.getElement().getStyle().setProperty("display", "-webkit-box");
      contactsPanel.getElement().getStyle().setProperty("WebkitBoxOrient", "vertical");
      contactsPanel.add(buttonContainer);

      animationHelper.goTo(contactsPanel, Animation.SLIDE);
   }

   private List<CellGroup<Header, Content>> buildList(final int contentSize) {
      final String dotLabel = "&middot;";
      final String[] labels = new String[] { "A", "B", dotLabel, "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#" };

      final ArrayList<CellGroup<Header, Content>> list = new ArrayList<CellGroup<Header, Content>>();

      for (int i = 0; i < labels.length; i++) {
         final Header header = new Header(labels[i]);
         final ArrayList<Content> arrayList = new ArrayList<Content>();

         if (!dotLabel.equals(labels[i])) {
            final int max = (int) (Math.random() * contentSize);
            for (int j = 0; j < max; j++) {
               arrayList.add(new Content("" + j));
            }
         }

         final CellGroup<Header, Content> cellGroup = new StandardCellGroup<Header, Content>(header.getName(), header, arrayList);
         list.add(cellGroup);
      }
      return list;
   }

}
