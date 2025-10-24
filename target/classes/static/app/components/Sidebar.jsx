
const { useEffect } = React;

function NavItem({href, icon, text}){
  const [active, setActive] = React.useState(false);
  useEffect(()=>{
    const check = () => setActive(location.hash === href);
    check();
    window.addEventListener('hashchange', check);
    return () => window.removeEventListener('hashchange', check);
  },[]);
  return (
    <a href={href} className={active ? 'active' : ''}>
      <span>{icon}</span><span>{text}</span>
    </a>
  );
}

function Sidebar(){
  return (
    <aside className="sidebar">
      <div className="row" style={{justifyContent:'space-between'}}>
        <strong>AirTickets</strong><span className="badge">beta</span>
      </div>
      <div style={{height:12}}/>
      <nav className="grid">
        <NavItem href="#/" icon="🏠" text="Inicio"/>
        <NavItem href="#/vuelos/buscar" icon="🔎" text="Vuelos"/>
        <NavItem href="#/reservas" icon="🎫" text="Reservaciones"/>
        <NavItem href="#/aerolineas" icon="🛫" text="Aerolíneas"/>
        <NavItem href="#/estadisticas" icon="📊" text="Estadísticas"/>
        <NavItem href="#/reclamos" icon="📝" text="Reclamos"/>
      </nav>
    </aside>
  );
}

export default NavItem;
