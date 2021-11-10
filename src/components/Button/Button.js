import "./Button.css"

const Button = (props) =>{
    return <button className ={`form-submit ${props.className}`} onClick = {props.onClick}>{props.title}</button>
}
export default Button