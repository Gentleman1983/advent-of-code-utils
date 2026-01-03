package de.havox_design.aoc.utils.java.search.breadth_first_search;

import java.util.ArrayList;
import java.util.List;

public  class AoC2015Day22StateWrapper<S> {

    private final S state;

    private final AoC2015Day22StateWrapper<S> predecessor;

    AoC2015Day22StateWrapper(final S state, final AoC2015Day22StateWrapper<S> predecessor) {
        this.state = state;
        this.predecessor = predecessor;
    }

    public S getState() {
        return state;
    }

    public AoC2015Day22StateWrapper<S> getPredecessor() {
        return predecessor;
    }

    public List<S> getStates() {
        final List<S> stateList;

        if (predecessor == null) {
            stateList = new ArrayList<>();
        } else {
            stateList = predecessor.getStates();
        }

        stateList.add(state);

        return stateList;
    }
}
