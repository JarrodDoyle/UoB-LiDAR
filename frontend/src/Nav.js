import React from 'react';

function navBtn(props){
  return <a herf="#">{props.name}</a>;
}

function Nav(){
  return (
    <nav>
      <navBtn name="hello"/>
      <navBtn name="wow"/>
    </nav>
  );
}

export default Nav;
