import React from "react"
import "../scheldule/AutoFillCss.css"
import "../scheldule/SchelduleCss.css"

const ScoreComponent = () => {

    return(
        <div>
            <h1 style={{borderBottom: "1px solid", margin: "1% 50% 1% 1%", borderBottom: "1px solid", textAlign: "left"}}>Kết quả học tập</h1>
            <div className="average-score" style={{borderBottom: "1px solid", margin: "1%"}}>
                    <h2>Điểm tổng hợp </h2>
                    <table className="table">
                        <thead style={{borderTop: "1px solid"}}>
                        <tr>
                            <th scope="col">Học kì</th>
                            <th scope="col">Số môn học</th>
                            <th scope="col">Điểm trung bình ở học kì</th>
                            <th scope="col">Điểm trung bình tích lũy</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1/2021</th>
                            <td>6</td>
                            <td>Otto</td>
                            <td>@mdo</td>
                        </tr>
                        <tr>
                            <th scope="row">2/2021</th>
                            <td>5</td>
                            <td>Thornton</td>
                            <td>@fat</td>
                        </tr>
                        <tr>
                            <th scope="row">3/2021</th>
                            <td >10</td>
                            <td>@twitter</td>
                            <td>@twitter</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div className="search-score" style={{margin: "margin:2% 1% 2% 1%"}}>
                    <h2 style={{margin: "margin:0.5% 0 1% 0", borderBottom: "1px solid"}}>Tìm kiếm điểm</h2>
                    <div className= "search-container">
                        <div className="autocomplete" style={{width: "300px", marginRight: "10px"}}>
                            <input id="myInput" type="text" name="myCountry" placeholder="Nhập môn học" />
                        </div>
                        <input type="submit" value="Tìm kiếm" />
                    </div>               
                </div>
                <div className="detail-score" style={{borderBottom: "1px solid", margin: "1%"}}>
                    <h2 style={{marginTop: "2%"}}>Điểm chi tiết</h2>
                    <table className="table detail-score" style={{color: "#333"}}>
                        <thead  style={{borderTop: "1px solid"}}>
                        <tr>
                            <th scope="col" className= "semester" >Học kì</th>
                            <th scope="col" className= "subject" style={{width: "30%" }}>Môn học</th>
                            <th scope="col">BT</th>
                            <th scope="col">GK</th>
                            <th scope="col">CK</th>
                            <th scope="col" colspan="2" >
                                <table width="100%">
                                    <thead>
                                        <tr>
                                            <th colspan="2" style={{borderBottom: "0.1px solid", marginBottom: "2%"}}>Điểm trung bình</th>
                                        </tr>
                                        <tr>
                                            <th scope="col" style={{borderRight: "0.1px solid", width: "50%"}}>Thang 10</th>
                                            <th scope="col" style={{ width: "50%"}}>Chữ</th>
                                        </tr>
                                    </thead>
                                </table>
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1/2021</th>
                            <td>Toán ứng dụng công nghệ Thông tin</td>
                            <td>10</td>
                            <td>10</td>
                            <td>10</td>
                            <td >10</td>
                            <td >A</td>
                        </tr>
                        <tr>
                            <th scope="row">1/2021</th>
                            <td>Cấu trúc dữ liệu</td>
                            <td>9</td>
                            <td>9</td>
                            <td>9</td>
                            <td>10</td>
                            <td>A</td>
                        </tr>
                        <tr>
                            <th scope="row">1/2021</th>
                            <td>Hệ thống thông tin</td>
                            <td>@mdo</td>
                            <td >Larry the Bird</td>
                            <td>@twitter</td>
                            <td>@twitter</td>
                            <td>@twitter</td>
                        </tr>
                        </tbody>
                </table>
            </div>
        </div>
    )
}

export default ScoreComponent;