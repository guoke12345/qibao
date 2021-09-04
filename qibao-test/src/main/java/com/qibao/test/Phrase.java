package com.qibao.test;


/**
 * 成语类
 *
 * @author qibao
 * @version v0.1 2021/8/25
 */
public class Phrase {
    private String name;
    private String spell;
    private String meaning;
    private String reference;
    private String example;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    @Override
    public String toString() {
        return "Phrase{" +
                "name='" + name + '\'' +
                ", spell='" + spell + '\'' +
                ", meaning='" + meaning + '\'' +
                ", reference='" + reference + '\'' +
                ", example='" + example + '\'' +
                '}';
    }
}
