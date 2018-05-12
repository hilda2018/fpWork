import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  'base-footer': {
    'borderTop': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#ddd' }],
    'position': 'relative',
    'background': '#fff',
    'zoom': '1',
    'zIndex': '9999',
    'clear': 'both'
  },
  'base-footer row': {
    'position': 'absolute',
    'left': [{ 'unit': '%H', 'value': 0.5 }],
    'top': [{ 'unit': 'px', 'value': 0 }],
    'marginLeft': [{ 'unit': 'px', 'value': -425 }],
    'width': [{ 'unit': 'px', 'value': 825 }],
    'height': [{ 'unit': 'px', 'value': 140 }]
  },
  'base-footer footer-left-content': {
    'display': 'inline-block',
    'marginBottom': [{ 'unit': 'px', 'value': 50 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'fontSize': [{ 'unit': 'px', 'value': 14 }],
    'fontFamily': 'Open Sans,sans-serif',
    'lineHeight': [{ 'unit': 'em', 'value': 1.5 }]
  },
  'base-footer footer-left-content img': {
    'width': [{ 'unit': 'px', 'value': 10 }],
    'height': [{ 'unit': 'string', 'value': 'auto' }],
    'width': [{ 'unit': 'px', 'value': 10 }],
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'float': 'none'
  },
  'base-footer copyright': {
    'position': 'absolute',
    'left': [{ 'unit': '%H', 'value': 0.5 }],
    'top': [{ 'unit': 'px', 'value': 172 }],
    'marginLeft': [{ 'unit': 'px', 'value': -425 }],
    'background': '#fff',
    'width': [{ 'unit': 'px', 'value': 825 }],
    'height': [{ 'unit': 'px', 'value': 14 }],
    'height': [{ 'unit': 'px', 'value': 40 }],
    'borderTop': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#eee' }],
    'color': '#888',
    'textAlign': 'center',
    'fontSize': [{ 'unit': 'px', 'value': 9 }],
    'lineHeight': [{ 'unit': 'px', 'value': 40 }]
  },
  'base-footer a': {
    'display': 'inline-block',
    'marginBottom': [{ 'unit': 'px', 'value': 5 }],
    'height': [{ 'unit': 'px', 'value': 20 }],
    'color': '#555',
    'textDecoration': 'none',
    'lineHeight': [{ 'unit': 'px', 'value': 20 }]
  },
  'base-footer p': {
    'marginTop': [{ 'unit': 'px', 'value': 6 }],
    'paddingRight': [{ 'unit': 'px', 'value': 22 }],
    'height': [{ 'unit': 'px', 'value': 72 }],
    'borderRight': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#eee' }],
    'color': '#888',
    'textAlign': 'left',
    'fontSize': [{ 'unit': 'px', 'value': 9 }]
  },
  'base-footer about p': {
    'borderRight': [{ 'unit': 'px', 'value': 0 }]
  },
  'base-footer click': {
    'color': '#0083d4'
  },
  'base-footer col-2': {
    'float': 'left',
    'marginTop': [{ 'unit': 'px', 'value': 18 }],
    'paddingRight': [{ 'unit': 'px', 'value': 22 }],
    'width': [{ 'unit': 'px', 'value': 210 }],
    'height': [{ 'unit': 'px', 'value': 120 }]
  },
  'base-footer col-2 h4': {
    'display': 'block',
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'height': [{ 'unit': 'px', 'value': 40 }],
    'color': '#8bc34a',
    'textTransform': 'uppercase',
    'letterSpacing': [{ 'unit': 'px', 'value': 0.5 }],
    'fontWeight': '600',
    'fontSize': [{ 'unit': 'px', 'value': 1 }],
    'fontFamily': 'Open Sans,sans-serif',
    'lineHeight': [{ 'unit': 'px', 'value': 40 }],
    'overflowWrap': 'break-word'
  },
  'base-footer': {
    'position': 'relative',
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'height': [{ 'unit': 'px', 'value': 220 }],
    'overflow': 'hidden',
    'backgroundColor': '#fff',
    'zIndex': '999999'
  },
  'col-area': {
    'float': 'right'
  },
  'col-area img': {
    'height': [{ 'unit': 'px', 'value': 120 }],
    'border': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#eee' }],
    'boxShadow': [{ 'unit': 'string', 'value': 'inset' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 16 }, { 'unit': 'string', 'value': 'rgba(255, 255, 255, 0.6)' }, { 'unit': 'string', 'value': 'rgba(255, 255, 255, 0.6),' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 1 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, 0.3)' }],
    'width': [{ 'unit': 'px', 'value': 400 }],
    'marginTop': [{ 'unit': 'px', 'value': 18 }],
    'borderRadius': '2px'
  },
  '#LoginA #usernameInfo': {
    'width': [{ 'unit': 'px', 'value': 100 }],
    'textOverflow': 'ellipsis',
    'whiteSpace': 'nowrap',
    'height': [{ 'unit': 'px', 'value': 28 }],
    'display': 'inline-block',
    'overflow': 'hidden',
    'textAlign': 'left',
    'verticalAlign': 'middle'
  },
  'is-button': {
    'backgroundColor': '#00B894',
    'color': '#FFF',
    'WebkitBoxShadow': '0 1px 2px 0 rgba(100, 100, 100, 0.5)',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 1 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(100, 100, 100, 0.5)' }],
    'WebkitTransition': 'all 200ms',
    'transition': 'all 200ms',
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }],
    'border': [{ 'unit': 'string', 'value': 'none' }],
    'borderRadius': '0',
    'overflow': 'visible',
    'font': [{ 'unit': 'string', 'value': 'inherit' }],
    'fontFamily': 'Arial, Verdana, sans-serif',
    'textTransform': 'none',
    'display': 'inline-block',
    'WebkitBoxSizing': 'border-box',
    'boxSizing': 'border-box',
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'rem', 'value': 1.5 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'rem', 'value': 1.5 }],
    'verticalAlign': 'middle',
    'fontSize': [{ 'unit': 'rem', 'value': 1 }],
    'lineHeight': [{ 'unit': 'px', 'value': 2.6 }],
    'textAlign': 'center',
    'textDecoration': 'none',
    'WebkitTransition': 'all 200ms',
    'transition': 'all 200ms',
    'borderRadius': '4px',
    'fontSize': [{ 'unit': 'px', 'value': 15 }],
    'fontFamily': ''graphik-medium'',
    'marginBottom': [{ 'unit': 'rem', 'value': 1 }],
    'verticalAlign': 'unset'
  },
  'is-button:hover': {
    'WebkitBoxShadow': '0 2px 2px 0 rgba(0, 0, 0, 0.3)',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, 0.3)' }]
  },
  '#how-it-works Slider Nav': {
    'WebkitBoxShadow': '0 2px 4px 0 rgba(125, 138, 148, 0.21)',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 4 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(125, 138, 148, 0.21)' }],
    'overflow': 'hidden',
    'backgroundColor': '#FFF',
    'zIndex': '999'
  },
  'how-it-works Slider NavDesktop': {
    'borderRadius': '9px',
    'marginRight': [{ 'unit': 'px', 'value': 24 }],
    'position': 'relative',
    'width': [{ 'unit': 'px', 'value': 260 }],
    'display': '-webkit-box',
    'display': '-ms-flexbox',
    'display': 'flex',
    'WebkitBoxPack': 'justify',
    'MsFlexPack': 'justify',
    'justifyContent': 'space-between',
    'bottom': [{ 'unit': 'px', 'value': 20 }],
    'borderRadius': '9px',
    'marginRight': [{ 'unit': 'px', 'value': 24 }],
    'position': 'relative',
    'width': [{ 'unit': 'px', 'value': 260 }],
    'display': '-webkit-box',
    'display': '-ms-flexbox',
    'display': 'flex',
    'WebkitBoxPack': 'justify',
    'MsFlexPack': 'justify',
    'justifyContent': 'space-between',
    'bottom': [{ 'unit': 'px', 'value': 20 }]
  },
  '#how-it-works Slider Nav': {
    'WebkitBoxShadow': '0 2px 4px 0 rgba(125, 138, 148, 0.21)',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 2 }, { 'unit': 'px', 'value': 4 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(125, 138, 148, 0.21)' }],
    'overflow': 'hidden',
    'backgroundColor': '#FFF',
    'zIndex': '999'
  },
  'is-position-bottom-center': {
    'bottom': [{ 'unit': 'px', 'value': 0 }]
  },
  'is-position-top-center': {
    'left': [{ 'unit': '%H', 'value': 0.5 }],
    'WebkitTransform': 'translateX(-50%)',
    'transform': 'translateX(-50%)',
    'display': 'table',
    'width': [{ 'unit': 'string', 'value': '-moz-max-content' }],
    'maxWidth': [{ 'unit': '%H', 'value': 1 }],
    'WebkitBoxSizing': 'border-box',
    'boxSizing': 'border-box'
  },
  'is-position-bottom-center': {
    'left': [{ 'unit': '%H', 'value': 0.5 }],
    'WebkitTransform': 'translateX(-50%)',
    'transform': 'translateX(-50%)',
    'display': 'table',
    'width': [{ 'unit': 'string', 'value': '-moz-max-content' }],
    'maxWidth': [{ 'unit': '%H', 'value': 1 }],
    'WebkitBoxSizing': 'border-box',
    'boxSizing': 'border-box'
  },
  '[class*='is-position-top']': {
    'position': 'absolute !important'
  },
  '[class*='is-position-bottom']': {
    'position': 'absolute !important'
  },
  '[class*='is-position-left']': {
    'position': 'absolute !important'
  },
  '[class*='is-position-right']': {
    'position': 'absolute !important'
  },
  '[class*='is-position-center']': {
    'position': 'absolute !important'
  },
  '*': {
    'WebkitTapHighlightColor': 'rgba(0, 0, 0, 0)',
    'WebkitTapHighlightColor': 'transparent',
    'outline': '0',
    'WebkitFontSmoothing': 'antialiased',
    'textShadow': [{ 'unit': 'string', 'value': 'rgba(0, 0, 0, 0.01)' }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, 0.01)' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 1 }]
  }
});
