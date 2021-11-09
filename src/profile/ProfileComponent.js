import React from "react"
import "./ProfileCss.css"


const ProfileComponent = () => {

    return(
        <div>
            <div>
                <h2 style={{marginLeft: "2%", marginTop: "1%"}}> Thông tin sinh viên</h2>
                <div style={{margin: "1%"}}>
                    <ul className="nav nav-tabs mb-3" id="myTab0" role="tablist">
                        <li className="nav-item" role="presentation">
                        <button
                            className="nav-link active"
                            id="profile-tab0"
                            data-mdb-toggle="tab"
                            data-mdb-target="#home0"
                            type="button"
                            role="tab"
                            aria-controls="home"
                            aria-selected="true"
                        >
                            Profile
                        </button>
                        </li>
                        <li className="nav-item" role="presentation">
                        <button
                            className="nav-link "
                            id="change-password-tab0"
                            data-mdb-toggle="tab"
                            data-mdb-target="#profile0"
                            type="button"
                            role="tab"
                            aria-controls="profile"
                            aria-selected="false"
                        >
                            Change Password
                        </button>
                        </li>
                    </ul>
                
                    <div className="tab-content" id="myTabContent0">
                        <div className="tab-pane fade show active" id="profile0" role="tabpanel" aria-labelledby="home-tab0">
                            <div className= "personal-profile">
                                <div className= "personal-avatar" style={{marginLeft: "1%"}}>
                                    <img src="/img/avatar.png" alt="avatar" style={{width: "80%", border: "1px solid black"}} />
                                </div>
                                <div className="personal-info">
                                    <div className="info-item" >
                                        <lable for="fullname"  style={{marginLeft: "5%"}}>Họ tên:</lable>
                                        <input name ="fullname" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="birthdate" style={{marginLeft: "11%"}}>Ngày sinh:</lable>
                                        <input name ="birthdate" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="birthplace" style={{marginLeft: "11%"}}>Nơi sinh:</lable>
                                        <input name ="birthplace" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="gender"  style={{marginLeft: "2%"}}>Giới tính:</lable>
                                        <input name ="gender" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="ethnicity"  style={{marginLeft: "15%"}}>Dân tộc:</lable>
                                        <input name ="ethnicity" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="nationality"  style={{marginLeft: "9%"}}>Quốc tịch:</lable>
                                        <input name ="nationality" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="idnumber">Số CMND:</lable>
                                        <input name ="fullname" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="iddate">Ngày cấp CMND:</lable>
                                        <input name ="iddate" type="text" className="profile-input"/>
                                    </div>
                                    <div className= "info-item">
                                        <lable for="idplace">Nơi cấp CMND:</lable>
                                        <input name ="idplace" type="text" className="profile-input"/>
                                    </div>
                                </div>
                            </div>
                            <div className= "study-info" >
                                <div className= "info-item"  style={{marginLeft: "9%"}}>
                                    <lable for="faculty"  style={{marginLeft: "6.7%"}}>Ngành:</lable>
                                    <input name ="faculty" type="text" className="profile-input"/>
                                </div>
                                <div className= "info-item">
                                    <lable for="className" style={{marginLeft: "1%"}}>Lớp:</lable>
                                    <input name ="className" type="text" className="profile-input"/>
                                </div>
                            </div>
                            <div className= "study-info"  style={{marginTop: "2%"}}>
                                <div className= "info-item" style={{marginLeft: "9%"}}>
                                    <lable for="phone">Số điện thoại:</lable>
                                    <input name ="phone" type="text" className="profile-input"/>
                                </div>
                                <div className= "info-item">
                                    <lable for="email">Email</lable>
                                    <input name ="email" type="text" className="profile-input"/>
                                </div>
                            </div>
                        </div>
                        <div className="tab-pane fade " id="change-password0" role="tabpanel" aria-labelledby="profile-tab0">
                            <form action="" method="POST"  id="form-register">
                                <div className="form-group">
                                    <label className="inputLabel" for="currentpassword">Curent password</label>
                                    <input type="password" id="currentpassword" name="currentpassword" rules ="required" className= "form-control" />
                                    <span className="form-message"></span>
                                </div>
                                <div className="form-group">
                                    <label className="inputLabel" for="password">New Password</label>
                                    <input type="password" id="password" name="password" rules ="required" className= "form-control" />
                                    <span className="form-message"></span>
                                </div>
                                    
                                <div className="form-group">
                                    <label className="inputLabel" for="confirmPassword">Confirm Password</label>
                                    <input type="password" id="confirmPassword" name="confirmPassword" rules = "confirm:password" className= "form-control" />
                                    <span className="form-message"></span>
                                </div>
                                
                                <div className="buttonWrapper">
                                    <button type="submit" id="submitButton" className="submitButton pure-button pure-button-primary">
                                        <span><b>Submit</b></span>
                                    </button>
                                </div>
                            </form>
                            
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}
export default ProfileComponent;