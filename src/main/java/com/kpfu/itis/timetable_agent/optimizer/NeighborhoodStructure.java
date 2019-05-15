package com.kpfu.itis.timetable_agent.optimizer;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

public enum NeighborhoodStructure {
    SWAP_MOVE(1),
    SHIFT_MOVE(2),
    ROOM_MOVE(3);

    private int num;
    private static Map map = new HashMap<>();

    NeighborhoodStructure(int num) {
        this.num = num;
    }

    static {
        for (NeighborhoodStructure neighborhoodStructure : NeighborhoodStructure.values()) {
            map.put(neighborhoodStructure.num, neighborhoodStructure);
        }
    }

    public static NeighborhoodStructure get(int neighborhoodStructure) {
        return (NeighborhoodStructure) map.get(neighborhoodStructure);
    }
}
