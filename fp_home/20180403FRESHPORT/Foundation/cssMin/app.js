import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  'svgWrap': {
    'width': [{ 'unit': 'px', 'value': 60 }],
    'height': [{ 'unit': 'px', 'value': 60 }],
    'textAlign': 'center',
    'backgroundColor': '#fff',
    'WebkitBoxShadow': 'inset 0 0 4px 0 rgba(0,0,0,.08)',
    'boxShadow': [{ 'unit': 'string', 'value': 'inset' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 4 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(0,0,0,.08)' }],
    'lineHeight': [{ 'unit': 'px', 'value': 68 }],
    'fontSize': [{ 'unit': 'px', 'value': 30 }],
    'verticalAlign': 'middle',
    'margin': [{ 'unit': 'px', 'value': 8 }, { 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 6 }],
    'boxSizing': 'content-box',
    'borderColor': '#f8f8f8',
    'position': 'relative',
    'borderWidth': '4px',
    'borderStyle': 'solid',
    'display': 'inline-block',
    'WebkitBorderRadius': '100%',
    'borderRadius': '100%',
    'backgroundImage': 'url(http://betheme.muffingroupsc.netdna-cdn.com/be/fit/wp-content/themes/betheme/images/stripes/stripes_3_b.png)'
  },
  'svgWrap::after': {
    'content': '""',
    'display': 'block',
    'width': [{ 'unit': '%H', 'value': 1 }],
    'height': [{ 'unit': '%V', 'value': 1 }],
    'borderWidth': '1px',
    'borderStyle': 'solid',
    'borderColor': '#e2e2e2',
    'WebkitBorderRadius': '100%',
    'borderRadius': '100%',
    'WebkitBoxSizing': 'border-box',
    'MozBoxSizing': 'border-box',
    'boxSizing': 'border-box',
    'position': 'absolute',
    'left': [{ 'unit': 'px', 'value': 0 }],
    'top': [{ 'unit': 'px', 'value': 0 }]
  },
  'svgWrap svg': {
    'overflow': 'hidden',
    'height': [{ 'unit': 'px', 'value': 30 }],
    'fill': '#409843',
    'width': [{ 'unit': 'px', 'value': 56 }]
  },
  'body': {
    'color': '#373a3c',
    'fontSize': [{ 'unit': 'rem', 'value': 1 }],
    'lineHeight': [{ 'unit': 'px', 'value': 1.5 }]
  },
  'html': {
    'color': '#373a3c',
    'fontSize': [{ 'unit': 'rem', 'value': 1 }],
    'lineHeight': [{ 'unit': 'px', 'value': 1.5 }]
  },
  'servicesClass a': {
    'display': 'block',
    'textAlign': 'center',
    'boxSizing': 'content-box'
  },
  'servicesClass div:hover span': {
    'background': '#409843',
    'fill': '#fff'
  },
  'servicesClass div:hover span svg': {
    'fill': '#fff'
  },
  'serviceDetail': {
    'textTransform': 'uppercase',
    'display': 'block',
    'marginTop': [{ 'unit': 'em', 'value': 0.8 }],
    'fontSize': [{ 'unit': 'em', 'value': 0.8 }],
    'textAlign': 'center',
    'paddingBottom': [{ 'unit': 'em', 'value': 1 }],
    'fontWeight': '400',
    'color': '#1d2025',
    'letterSpacing': [{ 'unit': 'px', 'value': 0.5 }]
  },
  'servicesClass': {
    'maxHeight': [{ 'unit': 'px', 'value': 146 }],
    'height': [{ 'unit': 'string', 'value': 'auto' }],
    'overflow': 'hidden',
    'position': 'relative',
    'borderRight': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0,0,0,.08)' }],
    'borderTop': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0,0,0,.08)' }],
    'textAlign': 'center'
  },
  'serviceWrap': {
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0,0,0,.08)' }],
    'borderLeft': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0,0,0,.08)' }],
    'maxWidth': [{ 'unit': '%H', 'value': 1 }]
  },
  'serviceDetail:hover': {
    'color': '#8bc34a'
  },
  'association': {
    'marginTop': [{ 'unit': 'em', 'value': 1.4 }]
  }
});
