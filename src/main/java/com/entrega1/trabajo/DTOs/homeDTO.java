package com.entrega1.trabajo.DTOs;

import com.entrega1.trabajo.model.Referee;
import com.entrega1.trabajo.model.RefereeRequest;
import java.util.List;

public class homeDTO {

    private Referee referee;
    private List<RefereeRequest> refereeRequest;

    homeDTO () {}

    public homeDTO(Referee referee, List<RefereeRequest> refereeRequest){
        this.referee = referee;
        this.refereeRequest = refereeRequest;
    }

    public Referee getRefereeInfo() {
        return referee;
    }

    public void setRefereeInfo(Referee referee) {
        this.referee = referee;
    }

    public List<RefereeRequest> getRefereeRequests() {
        return refereeRequest;
    }

    public void setRefereeRequests(List<RefereeRequest> refereeRequest) {
        this.refereeRequest = refereeRequest;
    }


}
