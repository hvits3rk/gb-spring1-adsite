package com.romantupikov.pagination;

import com.romantupikov.entity.Ad;

import java.util.List;

public class AdPaginationModel {
    private List<Ad> adList;

    public List<Ad> getAdList() {
        return adList;
    }

    public void setAdList(List<Ad> adList) {
        this.adList = adList;
    }
}
