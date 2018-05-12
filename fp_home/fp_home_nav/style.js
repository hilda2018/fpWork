import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  'a': {
    'textDecoration': 'none',
    'color': '#4988C6'
  },
  'p': {
    'color': '#444'
  },
  'h1 span': {
    'color': '#ddd',
    'fontSize': [{ 'unit': 'px', 'value': 26 }]
  },
  'body': {
    'fontFamily': '"Helvetica Neue"'
  },
  'container': {
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }],
    'width': [{ 'unit': '%H', 'value': 0.9 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': '%H', 'value': 0.05 }, { 'unit': 'px', 'value': 20 }, { 'unit': '%H', 'value': 0.05 }],
    'maxWidth': [{ 'unit': 'px', 'value': 1200 }],
    'position': 'relative',
    'paddingLeft': [{ 'unit': 'px', 'value': 100 }]
  },
  'menu_container': {
    'display': 'block',
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }],
    'margin': [{ 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }],
    'background': '#fff',
    'height': [{ 'unit': 'px', 'value': 56 }],
    'MozBoxShadow': '0 2px 2px rgba(0,0,0,0.075)',
    'WebkitBoxShadow': '0 2px 2px rgba(0,0,0,0.075)',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'string', 'value': 'rgba(0,0,0,0.075)' }]
  },
  'menu_container a': {
    'textDecoration': 'none',
    'color': '#333',
    'fontWeight': '800',
    'fontSize': [{ 'unit': 'px', 'value': 12 }],
    'display': 'block',
    'height': [{ 'unit': 'px', 'value': 36 }],
    'lineHeight': [{ 'unit': 'px', 'value': 36 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 15 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 15 }],
    'textTransform': 'capitalize'
  },
  'menu_container ul': {
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'listStyle': 'none',
    'float': 'right',
    'padding': [{ 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 0 }]
  },
  'menu_container ul licurrent-menu-item a': {
    'color': '#8bc34a'
  },
  'menu_container > ul > li': {
    'display': 'block',
    'float': 'left',
    'position': 'relative'
  },
  'menu_container ul li:hover': {
    'color': '#8bc34a'
  },
  'menu_container > ul > li > ul > li > ul > li': {
  },
  'content': {
    'clear': 'both'
  },
  'title': {
    'fontWeight': '800',
    'fontSize': [{ 'unit': 'px', 'value': 12 }],
    'textTransform': 'uppercase'
  },
  'menu_container i svg': {
    'position': 'absolute',
    'left': [{ 'unit': '%H', 'value': 0.05 }],
    'width': [{ 'unit': 'px', 'value': 100 }],
    'top': [{ 'unit': 'px', 'value': 0 }]
  },
  'responsive_menu select': {
    'display': 'block',
    'width': [{ 'unit': '%H', 'value': 1 }],
    'height': [{ 'unit': 'px', 'value': 36 }],
    'padding': [{ 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 12 }, { 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 12 }],
    'fontSize': [{ 'unit': 'px', 'value': 14 }],
    'lineHeight': [{ 'unit': 'px', 'value': 1.42857 }],
    'color': 'rgb(85, 85, 85)',
    'verticalAlign': 'middle',
    'backgroundColor': 'rgb(255, 255, 255)',
    'backgroundImage': 'none',
    'border': [{ 'unit': 'string', 'value': 'none' }]
  },
  'nofull select': {
    'width': [{ 'unit': 'string', 'value': 'auto' }]
  },
  'label': {
    'float': 'left',
    'lineHeight': [{ 'unit': 'px', 'value': 36 }],
    'fontWeight': '800',
    'fontSize': [{ 'unit': 'px', 'value': 12 }]
  },
  'note': {
    'color': '#999',
    'fontSize': [{ 'unit': 'px', 'value': 14 }]
  },
  'download_button': {
    'padding': [{ 'unit': 'px', 'value': 15 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 15 }, { 'unit': 'px', 'value': 0 }],
    'background': '#F2F2F2',
    'display': 'block',
    'margin': [{ 'unit': 'px', 'value': 30 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 30 }, { 'unit': 'string', 'value': 'auto' }],
    'width': [{ 'unit': '%H', 'value': 1 }],
    'maxWidth': [{ 'unit': 'px', 'value': 200 }],
    'textAlign': 'center',
    'color': '#4988C6',
    'textDecoration': 'none',
    'fontWeight': '800',
    'fontSize': [{ 'unit': 'px', 'value': 12 }],
    'borderBottom': [{ 'unit': 'px', 'value': 3 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#ddd' }]
  },
  'download_button:active': {
    'position': 'relative',
    'top': [{ 'unit': 'px', 'value': 3 }],
    'borderBottom': [{ 'unit': 'px', 'value': 0 }]
  },
  'download_button:hover': {
    'color': '#8bc34A'
  },
  'container': {
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }],
    'width': [{ 'unit': '%H', 'value': 0.9 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': '%H', 'value': 0.05 }, { 'unit': 'px', 'value': 20 }, { 'unit': 'px', 'value': 100 }],
    'maxWidth': [{ 'unit': 'px', 'value': 1100 }],
    'paddingLeft': [{ 'unit': 'px', 'value': 100 }]
  },
  '#indexSearch': {
    'display': 'inline-block',
    'width': [{ 'unit': 'px', 'value': 40 }],
    'textAlign': 'right',
    'borderRight': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#ccc' }],
    'paddingLeft': [{ 'unit': 'px', 'value': 50 }]
  },
  '#indexSearch svg': {
    'width': [{ 'unit': 'px', 'value': 24 }],
    'marginTop': [{ 'unit': 'px', 'value': 6 }],
    'height': [{ 'unit': 'px', 'value': 24 }],
    'fill': '#8bc34a'
  },
  'search': {
    'position': 'relative',
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }],
    'width': [{ 'unit': 'px', 'value': 526 }],
    'height': [{ 'unit': 'px', 'value': 35 }],
    'borderRight': [{ 'unit': 'string', 'value': 'none' }],
    'borderRadius': '50px',
    'background': '#fff'
  },
  'search select': {
    'display': 'none'
  },
  'search select_box': {
    'position': 'relative',
    'float': 'left',
    'width': [{ 'unit': 'px', 'value': 75 }],
    'color': '#555',
    'fontSize': [{ 'unit': 'px', 'value': 9 }],
    'lineHeight': [{ 'unit': 'px', 'value': 35 }]
  },
  'search select_showbox': {
    'height': [{ 'unit': 'px', 'value': 35 }],
    'background': 'url(../images/search_ico.png) 60px center no-repeat',
    'textIndent': '1.5em'
  },
  '#indexSearch:hover': {
    'cursor': 'pointer',
    'borderBottom': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#fff' }]
  },
  '#nav china': {
    'display': 'inline-block',
    'marginLeft': [{ 'unit': 'px', 'value': -40 }],
    'width': [{ 'unit': 'px', 'value': 20 }],
    'height': [{ 'unit': 'px', 'value': 20 }],
    'borderRadius': '50%',
    'lineHeight': [{ 'unit': 'px', 'value': 20 }]
  },
  'search select_option': {
    'position': 'absolute',
    'top': [{ 'unit': 'px', 'value': 35 }],
    'left': [{ 'unit': 'px', 'value': -1 }],
    'zIndex': '99',
    'display': 'none',
    'width': [{ 'unit': 'px', 'value': 75 }],
    'border': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#8bc34a' }],
    'borderRadius': '2px',
    'background': '#fff'
  },
  '#page': {
    'position': 'absolute',
    'left': [{ 'unit': '%H', 'value': 0.5 }]
  },
  'nav': {
    'position': 'absolute',
    'left': [{ 'unit': '%H', 'value': 0.5 }]
  },
  'search select_option li': {
    'width': [{ 'unit': '%H', 'value': 1 }],
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'dotted' }, { 'unit': 'string', 'value': '#bebebe' }],
    'textIndent': '1.5em',
    'cursor': 'pointer'
  },
  'search select_option liselected': {
    'backgroundColor': '#f5f5f5',
    'color': '#333'
  },
  'search inputbtn_srh': {
    'float': 'left',
    'height': [{ 'unit': 'px', 'value': 35 }],
    'border': [{ 'unit': 'string', 'value': 'none' }],
    'borderLeft': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#aaa' }],
    'background': '0 0',
    'lineHeight': [{ 'unit': 'px', 'value': 35 }],
    'cursor': 'pointer'
  },
  'search inputinp_srh': {
    'float': 'left',
    'height': [{ 'unit': 'px', 'value': 35 }],
    'border': [{ 'unit': 'string', 'value': 'none' }],
    'borderLeft': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#aaa' }],
    'background': '0 0',
    'lineHeight': [{ 'unit': 'px', 'value': 35 }],
    'cursor': 'pointer'
  },
  'search inputinp_srh': {
    'paddingLeft': [{ 'unit': 'px', 'value': 10 }],
    'width': [{ 'unit': 'px', 'value': 365 }],
    'outline': '0',
    'color': '#666',
    'cursor': 'pointer'
  },
  'search inputbtn_srh': {
    'top': [{ 'unit': 'px', 'value': 0 }],
    'right': [{ 'unit': 'px', 'value': 0 }],
    'zIndex': '99999999999',
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 14 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 14 }],
    'width': [{ 'unit': 'px', 'value': 80 }],
    'borderRadius': '0 50px 50px 0',
    'background': '#8bc34a',
    'color': '#fff',
    'letterSpacing': [{ 'unit': 'px', 'value': 1 }]
  },
  '#searchClose': {
    'position': 'absolute',
    'fontSize': [{ 'unit': 'px', 'value': 14 }],
    'cursor': 'pointer'
  },
  'search inputbtn_srh': {
    'position': 'absolute',
    'fontSize': [{ 'unit': 'px', 'value': 14 }],
    'cursor': 'pointer'
  },
  '#searchClose': {
    'top': [{ 'unit': 'px', 'value': 10 }],
    'right': [{ 'unit': 'px', 'value': 10 }],
    'width': [{ 'unit': 'px', 'value': 26 }],
    'height': [{ 'unit': 'px', 'value': 26 }],
    'borderRadius': '2px',
    'color': 'red',
    'letterSpacing': [{ 'unit': 'px', 'value': 2 }],
    'lineHeight': [{ 'unit': 'px', 'value': 26 }]
  },
  'quickLink': {
    'color': '#fff',
    'fontSize': [{ 'unit': 'px', 'value': 9 }]
  },
  'quickLinkALl': {
    'color': '#fff',
    'fontSize': [{ 'unit': 'px', 'value': 9 }]
  },
  'quickLink': {
    'marginTop': [{ 'unit': 'px', 'value': 9 }],
    'textAlign': 'center'
  },
  'quickLinkALl': {
    'position': 'absolute',
    'width': [{ 'unit': '%H', 'value': 1 }],
    'height': [{ 'unit': 'px', 'value': 50 }],
    'lineHeight': [{ 'unit': 'px', 'value': 50 }]
  },
  'quickLinkALl a': {
    'margin': [{ 'unit': 'px', 'value': 10 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 10 }, { 'unit': 'string', 'value': 'auto' }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }],
    'color': '#666',
    'letterSpacing': [{ 'unit': 'px', 'value': 0.5 }]
  },
  'quickLinkALl span': {
    'color': '#f58400',
    'fontWeight': 'bolder'
  },
  '#page': {
    'top': [{ 'unit': 'px', 'value': 86 }],
    'zIndex': '99999',
    'display': 'none',
    'margin': [{ 'unit': 'px', 'value': 20 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 20 }, { 'unit': '%H', 'value': -0.25 }],
    'width': [{ 'unit': 'px', 'value': 50 }],
    'height': [{ 'unit': 'px', 'value': 110 }],
    'borderRadius': '4px',
    'background': '#fff',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'string', 'value': 'rgba(0,0,0,.1)' }]
  },
  'open #page': {
    'display': 'block'
  },
  'search': {
    'border': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#8bc34a' }]
  },
  '#sign': {
    'marginLeft': [{ 'unit': 'px', 'value': 20 }]
  },
  'btn': {
    'background': '#fff',
    'borderRadius': '2px',
    'WebkitBoxShadow': '0 1px 2px rgba(0,0,0,.07)',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 1 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'string', 'value': 'rgba(0,0,0,.07)' }],
    'color': '#8bc34a!important',
    'display': 'block',
    'display': 'inline-block',
    'border': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#8bc34a' }],
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }],
    'fontSize': [{ 'unit': 'px', 'value': 15 }],
    'fontWeight': '500',
    'color': '#8bc34a',
    'lineHeight': [{ 'unit': 'px', 'value': 40 }],
    'minWidth': [{ 'unit': 'px', 'value': 60 }],
    'position': 'relative',
    'textAlign': 'center',
    'textDecoration': 'none'
  },
  'green': {
    'background': '#8bc34a',
    'color': '#fff!important'
  }
});
