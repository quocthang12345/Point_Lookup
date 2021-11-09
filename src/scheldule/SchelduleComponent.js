import React from "react"
import "../scheldule/AutoFillCss.css"
import "../scheldule/SchelduleCss.css"

const SchelduleComponent = () => {
    return(
        <div>
            <div>
                <h2 style={{margin: "1%", borderBottom: "1px solid"}}>Lịch học, lịch thi</h2>
                <div className="select-semester" style={{margin: "1%", borderBottom: "1px solid"}} >
                    <select style={{marginBottom: "1%", textIndent: "5%", width: "15%", fontSize: "large"}}>
                        <option  value="Học kì 1/2021">Học kì 1/2021</option>
                        <option selected value="Học kì 2/2021">Học kì 2/2021</option>
                        <option  value="Học kì 3/2021">Học kì 3/2021</option>
                        <option  value="Học kì 4/2021">Học kì 4/2021</option>
                    </select>
                    <button type="button" className="login-btn" style={{border:"none", borderRadius:"5px", marginLeft:"1%"}}>Dữ liệu</button>
                </div>
                <div className="average-score" style={{borderBottom: "1px solid", margin: "1%"}}>
                    <h5>Lịch học </h5>
                    <table className="table">
                        <thead style={{borderTop: "1px solid"}}>
                        <tr>
                            <th scope="col">Môn học</th>
                            <th scope="col">Giáo viên</th>
                            <th scope="col">Thời khóa biểu</th>
                            <th scope="col">Tuần học</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">Toán</th>
                            <td>Nguyễn Thị A</td>
                            <td>Thứ 2,7-10,F306; Thứ 3,1-4,F209</td>
                            <td>45-50</td>
                        </tr>
                        <tr>
                            <th scope="row">Lý</th>
                            <td>Nguyễn Thị A</td>
                            <td>Thứ 2,7-10,F306; Thứ 3,1-4,F209</td>
                            <td>45-50</td>
                        </tr>
                        <tr>
                            <th scope="row">Hóa</th>
                            <td >Nguyễn Thị A</td>
                            <td>Thứ 2,7-10,F306; Thứ 3,1-4,F209</td>
                            <td>45-50r</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                
                <div className="detail-score"  style={{borderBottom: "1px solid", margin:"1%"}}>
                    <h5 style={{marginTop: "2%"}}>Lịch thi</h5>
                    <table className="table"  style={{color: "#333"}}>
                        <thead style={{borderTop: "1px solid"}}>
                        <tr>
                            <th scope="col" className="semester" >STT</th>
                            <th scope="col" className="subject" >Môn học</th>
                            <th scope="col">Nhóm thi</th>
                            <th scope="col">Thi chung</th>
                            <th scope="col">Lịch thi cuối kì</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <th scope="row">1</th>
                            <td>Toán ứng dụng công nghệ Thông tin</td>
                            <td>10</td>
                            <td>10</td>
                            <td >Ngày: 05/09/2021, Phòng: , Giờ: 15h00, Xuất: 2C4</td>
                            
                        </tr>
                        <tr>
                            <th scope="row">2</th>
                            <td>Cấu trúc dữ liệu</td>
                            <td>9</td>
                            <td>9</td>
                            <td>Ngày: 05/09/2021, Phòng: , Giờ: 15h00, Xuất: 2C4</td>
                            
                        </tr>
                        <tr>
                            <th scope="row">3</th>
                            <td>Hệ thống thông tin</td>
                            <td>@mdo</td>
                            <td >Larry the Bird</td>
                            <td>Ngày: 05/09/2021, Phòng: , Giờ: 15h00, Xuất: 2C4</td>
                            
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    )
}
export default SchelduleComponent;