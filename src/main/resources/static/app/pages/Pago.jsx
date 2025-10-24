
function Pago({amount=0, onConfirm}){
  const [method,setMethod]=React.useState('CARD');
  return (
    <div className="container">
      <div className="glass card">
        <div className="title">Información de Pago</div>
        <div className="grid grid-2" style={{marginTop:12}}>
          <div><div className="small">Total a pagar</div><input value={amount} readOnly/></div>
          <div><div className="small">Método de pago</div>
            <select value={method} onChange={e=>setMethod(e.target.value)}>
              <option value="CARD">Tarjeta</option>
              <option value="CASH">Efectivo</option>
            </select>
          </div>
        </div>
        <div className="row" style={{marginTop:12}}>
          <button onClick={()=>history.back()}>Volver</button>
          <button className="primary" onClick={()=>onConfirm(method)}>Confirmar</button>
        </div>
      </div>
    </div>
  );
}

export default Pago;
