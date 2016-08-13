package com.ocp.assessment;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by helangovan on 1/9/16.
 */
public final class DuckAST6 {
    private String name; //make final
    private List<DuckAST6> ducklings;

    public DuckAST6(String name,List<DuckAST6> ducklings){
        this.name = name;
        this.ducklings = new ArrayList<>(ducklings);
    }

    public String getName() {
        return name;
    }

    public List<DuckAST6> getDucklings() {
        return new ArrayList<>(ducklings); //return new arraylist instead of giving the same object
    }

    public boolean hasDuckings(Predicate<DuckAST6> p){
        return p.test(this);
    }
}
