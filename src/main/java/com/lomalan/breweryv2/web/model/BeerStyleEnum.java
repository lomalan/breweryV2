package com.lomalan.breweryv2.web.model;

public enum BeerStyleEnum {
    LAGER("LAGER"), PILSNER("PILSNER"), STOUT("STOUT"), GOSE("GOSE"), PORTER("PORTER"), ALE("ALE"), WHEAT("WHEAT"), IPA("IPA"), PALE_ALE("PALE_ALE"), SAISON("SAISON");

    private String name;

    BeerStyleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
