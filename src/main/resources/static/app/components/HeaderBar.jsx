
function HeaderBar({email,onLogout}){
  return (
    <header className="header">
      <div className="row"><span style={{fontSize:'22px'}}>✈️</span><b>Airtickets</b></div>
      <div className="small">{email? <>Autenticado: {email} <button onClick={onLogout} style={{marginLeft:8,textDecoration:'underline'}}>Salir</button></> : 'No autenticado'}</div>
    </header>
  );
}

export default HeaderBar;
