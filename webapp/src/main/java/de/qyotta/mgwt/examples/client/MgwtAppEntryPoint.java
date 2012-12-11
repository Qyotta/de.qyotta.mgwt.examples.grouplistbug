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
import com.google.gwt.user.client.ui.RootPanel;
import com.googlecode.mgwt.mvp.client.Animation;
import com.googlecode.mgwt.ui.client.MGWT;
import com.googlecode.mgwt.ui.client.MGWTSettings;
import com.googlecode.mgwt.ui.client.animation.AnimationHelper;
import com.googlecode.mgwt.ui.client.util.SuperDevModeUtil;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.CellGroup;
import com.googlecode.mgwt.ui.client.widget.GroupingCellList.StandardCellGroup;
import com.googlecode.mgwt.ui.client.widget.HeaderList;
import com.googlecode.mgwt.ui.client.widget.LayoutPanel;
import com.googlecode.mgwt.ui.client.widget.tabbar.ContactsTabBarButton;
import com.googlecode.mgwt.ui.client.widget.tabbar.RootTabPanel;

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
