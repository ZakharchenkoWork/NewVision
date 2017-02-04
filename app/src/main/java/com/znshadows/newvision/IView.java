package com.znshadows.newvision;

import com.znshadows.newvision.models.Item;
import com.znshadows.newvision.models.ReposData;

import java.util.List;

/**
 * Created by kostya on 04.02.2017.
 */

public interface IView {
    void showList(List<Item> repoList);
    void showError(String error);
    void showEmptyList();
    String getUserName();
}
