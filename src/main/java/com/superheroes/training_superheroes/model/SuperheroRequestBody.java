package com.superheroes.training_superheroes.model;

import lombok.Builder;

@Builder
public class SuperheroRequestBody {
    private String superheroName;
    private String power;
    private String universe;

    public String getName() {
        return this.superheroName;
    }

    public String getPower() {
        return this.power;
    }

    public String getUniverse() {
        return this.universe;
    }

    public void setSuperheroName(String superheroName) {
        this.superheroName = superheroName;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setUniverse(String universe) {
        this.universe = universe;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof SuperheroRequestBody)) return false;
        final SuperheroRequestBody other = (SuperheroRequestBody) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$superheroName = this.getName();
        final Object other$superheroName = other.getName();
        if (this$superheroName == null ? other$superheroName != null : !this$superheroName.equals(other$superheroName))
            return false;
        final Object this$power = this.getPower();
        final Object other$power = other.getPower();
        if (this$power == null ? other$power != null : !this$power.equals(other$power)) return false;
        final Object this$universe = this.getUniverse();
        final Object other$universe = other.getUniverse();
        if (this$universe == null ? other$universe != null : !this$universe.equals(other$universe)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof SuperheroRequestBody;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $superheroName = this.getName();
        result = result * PRIME + ($superheroName == null ? 43 : $superheroName.hashCode());
        final Object $power = this.getPower();
        result = result * PRIME + ($power == null ? 43 : $power.hashCode());
        final Object $universe = this.getUniverse();
        result = result * PRIME + ($universe == null ? 43 : $universe.hashCode());
        return result;
    }

    public String toString() {
        return "SuperheroRequestBody(superheroName=" + this.getName() + ", power=" + this.getPower() + ", universe=" + this.getUniverse() + ")";
    }

}