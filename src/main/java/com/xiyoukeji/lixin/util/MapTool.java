package com.xiyoukeji.lixin.util;


import java.util.HashMap;

/**
 * Created by wangqiyun on 16/5/23.
 */
public class MapTool extends HashMap<String,Object> {
    @Override
    public MapTool put(String key, Object value) {
        super.put(key, value);
        return this;
    }

    public static MapTool Map() {
        return new MapTool();
    }

    public static MapTool Mapok() {
        MapTool mapTool = new MapTool();
        mapTool.put("state", "0");
        return mapTool;
    }


    public static MapTool Message(Integer objectId, String objectType, String operationType) {
        MapTool mapTool = Mapok();
        mapTool.put("msg", MapTool.Map().put("objectId",objectId).put("objectType",objectType).put("operationType",operationType));
        return mapTool;
    }



    public static MapTool Mapoktime() {
        MapTool mapTool = Mapok();
        mapTool.put("time", System.currentTimeMillis());
        return mapTool;
    }

}
