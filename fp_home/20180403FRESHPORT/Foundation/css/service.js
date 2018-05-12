import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  'svgWrap': {
    'width': [{ 'unit': 'px', 'value': 60 }],
    'height': [{ 'unit': 'px', 'value': 60 }],
    'textAlign': 'center',
    'backgroundColor': '#fff',
    'WebkitBoxShadow': 'inset 0 0 4px 0 rgba(0, 0, 0, .08)',
    'boxShadow': [{ 'unit': 'string', 'value': 'inset' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 4 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, .08)' }],
    'lineHeight': [{ 'unit': 'px', 'value': 68 }],
    'fontSize': [{ 'unit': 'px', 'value': 30 }],
    'verticalAlign': 'middle',
    'marginRight': [{ 'unit': 'px', 'value': 12 }],
    'position': 'relative',
    'boxSizing': 'content-box',
    'borderColor': '#f8f8f8',
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
    'position': 'absolute',
    'left': [{ 'unit': 'px', 'value': 0 }],
    'borderColor': '#e2e2e2',
    'top': [{ 'unit': 'px', 'value': 0 }],
    'WebkitBorderRadius': '100%',
    'borderRadius': '100%',
    'WebkitBoxSizing': 'border-box',
    'MozBoxSizing': 'border-box',
    'boxSizing': 'border-box'
  },
  'svgWrap svg': {
    'overflow': 'hidden',
    'height': [{ 'unit': 'px', 'value': 30 }],
    'fill': '#409843',
    'width': [{ 'unit': 'px', 'value': 56 }],
    'float': 'left',
    'marginTop': [{ 'unit': 'px', 'value': 14 }]
  },
  'orbit-bullets': {
    'position': 'absolute',
    'bottom': [{ 'unit': 'rem', 'value': 1 }],
    'marginTop': [{ 'unit': 'rem', 'value': 0.8 }],
    'marginBottom': [{ 'unit': 'rem', 'value': 0.8 }],
    'textAlign': 'center',
    'width': [{ 'unit': '%H', 'value': 0.8 }],
    'left': [{ 'unit': '%H', 'value': 0.5 }],
    'marginLeft': [{ 'unit': '%H', 'value': -0.4 }],
    'fontFamily': '"Roboto, Arial, Tahoma, sans-serif"'
  },
  'Service-module': {
    'height': [{ 'unit': 'rem', 'value': 6.375 }],
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, .08)' }]
  },
  'medium-4': {
    'float': 'left',
    'width': [{ 'unit': '%H', 'value': 0.33332999999999996 }]
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
  'nomarginLeft': {
    'display': 'block',
    'height': [{ 'unit': 'px', 'value': 100 }],
    'borderRight': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, .08)' }],
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(0, 0, 0, .08)' }],
    'textAlign': 'center',
    'background': '#fff',
    'boxSizing': 'content-box',
    'lineHeight': [{ 'unit': 'px', 'value': 100 }]
  },
  'nomarginLeft:hover span': {
    'background': '#409843',
    'fill': '#fff'
  },
  'nomarginLeft:hover span svg': {
    'fill': '#fff'
  },
  'serviceDetail': {
    'textTransform': 'uppercase',
    'fontWeight': '400',
    'display': 'inline-block',
    'fontSize': [{ 'unit': 'px', 'value': 13 }],
    'textAlign': 'center',
    'color': '#666',
    'fontWeight': '600',
    'color': '#1d2025',
    'letterSpacing': [{ 'unit': 'px', 'value': 0.5 }]
  },
  'nomarginLeft:hover svgWrap::after': {
    'display': 'none'
  }
});
