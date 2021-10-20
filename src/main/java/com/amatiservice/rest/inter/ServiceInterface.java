package com.amatiservice.rest.inter;

import com.amatiservice.model.BranchModel;
import com.amatiservice.model.RecessedModel;
import com.amatiservice.model.UserModel;

import javax.ws.rs.core.Response;
import java.util.List;

public interface ServiceInterface {

    Response retrieveModelBranch();
    Response retrieveModelUser();
    Response retrieveModelRecessed();

    Response saveUser(UserModel userModel);

    Response retrieveUserByEmail(UserModel userModelInput);

    Response createBranch(BranchModel branchModel);

    Response retrieveRecessedByBranch(BranchModel branchModel);
    Response retrieveBranchByUserMail(UserModel userModel);
    Response saveRecessed(RecessedModel recessedModel);


}
