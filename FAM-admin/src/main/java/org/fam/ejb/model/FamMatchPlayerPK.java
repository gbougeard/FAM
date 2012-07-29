package org.fam.ejb.model;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;

/**
 * @author gbougear
 */
public class FamMatchPlayerPK implements Serializable {

    private static final long serialVersionUID = -8409364271443197745L;
    private FamMatchTeamPK famMatchTeam;
    private Integer num;

    /**
     *
     */
    public FamMatchPlayerPK() {
    }

    public FamMatchTeamPK getFamMatchTeam() {
        return famMatchTeam;
    }

    public void setFamMatchTeam(FamMatchTeamPK famMatchTeam) {
        this.famMatchTeam = famMatchTeam;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FamMatchPlayerPK that = (FamMatchPlayerPK) o;

        if (famMatchTeam != null ? !famMatchTeam.equals(that.famMatchTeam) : that.famMatchTeam != null) {
            return false;
        }
        if (num != null ? !num.equals(that.num) : that.num != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = famMatchTeam != null ? famMatchTeam.hashCode() : 0;
        result = 31 * result + (num != null ? num.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("FamMatchPlayerPK");
        sb.append("{famMatchTeam=").append(famMatchTeam);
        sb.append(", num=").append(num);
        sb.append('}');
        return sb.toString();
    }
}
