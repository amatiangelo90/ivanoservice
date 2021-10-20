package com.amatiservice.dao;

import com.amatiservice.model.BranchModel;
import com.amatiservice.model.RecessedModel;
import com.amatiservice.model.UserModel;

import java.util.List;

public interface DaoInterface {

    void setupDatasource() throws Exception;
    long createUser(UserModel userModel) throws Exception;
    long createBranch(BranchModel branchModel) throws Exception;

    UserModel retrieveUserByEmail(String email) throws Exception;

    List<BranchModel> retrieveBranchesByEmail(String mail) throws Exception;
    long createRecessedAmountForSpecificBranch(RecessedModel recessedModel) throws Exception;

    List<RecessedModel> retrieveRecessedByBranchId(long pkBranchId) throws Exception;
}
