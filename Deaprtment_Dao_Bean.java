package com.example.online_examination_system.Admin_Bean;

import java.util.ArrayList;

public class Deaprtment_Dao_Bean
{
    ArrayList<String> dId=new ArrayList<>(),dName=new ArrayList<>();
    String did,dn;

    public ArrayList<String> getdId() {
        return dId;
    }

    public ArrayList<String> getdName() {
        return dName;
    }

    public void setDid(String did) {
        this.did = did;
        dId.add(did);
    }

    public void setDn(String dn) {
        this.dn = dn;
        dName.add(dn);
    }
}
