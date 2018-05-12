import { StyleSheet } from 'react-native';

export default StyleSheet.create({
  'tags span': {
    'textTransform': 'uppercase'
  },
  'titleArticle': {
    'textTransform': 'uppercase'
  },
  'download-link a': {
    'display': 'inline-block'
  },
  'download-link svg': {
    'display': 'inline-block'
  },
  'otherArticle a': {
    'display': 'inline-block'
  },
  'tags a': {
    'display': 'inline-block'
  },
  'articleWrap': {
    'overflow': 'hidden',
    'margin': [{ 'unit': 'px', 'value': 30 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 60 }, { 'unit': 'string', 'value': 'auto' }],
    'padding': [{ 'unit': 'px', 'value': 30 }, { 'unit': 'px', 'value': 30 }, { 'unit': 'px', 'value': 30 }, { 'unit': 'px', 'value': 30 }],
    'width': [{ 'unit': 'px', 'value': 750 }],
    'height': [{ 'unit': 'string', 'value': 'auto' }],
    'background': '#fff',
    'boxShadow': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 30 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'rgba(0,0,0,.2)' }]
  },
  'body': {
    'backgroundColor': '#f6f9fc'
  },
  'titleArticle': {
    'marginTop': [{ 'unit': 'px', 'value': 0 }],
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 20 }, { 'unit': 'px', 'value': 0 }],
    'width': [{ 'unit': '%H', 'value': 1 }],
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': 'rgba(33,33,33,.08)' }],
    'color': '#666',
    'textAlign': 'left',
    'letterSpacing': [{ 'unit': 'px', 'value': 1 }],
    'fontWeight': '700',
    'fontSize': [{ 'unit': 'px', 'value': 30 }],
    'lineHeight': [{ 'unit': 'px', 'value': 1.5 }]
  },
  'leftMain': {
    'float': 'left',
    'width': [{ 'unit': '%H', 'value': 1 }],
    'height': [{ 'unit': 'string', 'value': 'auto' }]
  },
  '#J_dotLine': {
    'position': 'absolute',
    'left': [{ 'unit': '%H', 'value': 0.5 }],
    'marginLeft': [{ 'unit': 'px', 'value': -550 }],
    'width': [{ 'unit': 'px', 'value': 825 }],
    'height': [{ 'unit': 'px', 'value': 200 }],
    'color': '#fff'
  },
  'articleHeader': {
    'position': 'relative',
    'top': [{ 'unit': 'px', 'value': -10 }],
    'left': [{ 'unit': 'px', 'value': 0 }],
    'zIndex': '1',
    'height': [{ 'unit': 'px', 'value': 150 }],
    'background': '#9ac26b'
  },
  'articleHeader h1': {
    'color': '#fff',
    'textAlign': 'center',
    'letterSpacing': [{ 'unit': 'px', 'value': 1 }],
    'fontSize': [{ 'unit': 'px', 'value': 50 }],
    'lineHeight': [{ 'unit': 'px', 'value': 150 }]
  },
  'h2': {
    'marginBottom': [{ 'unit': 'px', 'value': 0 }]
  },
  'articleDetail': {
    'height': [{ 'unit': 'px', 'value': 50 }],
    'color': '#999',
    'lineHeight': [{ 'unit': 'px', 'value': 50 }]
  },
  'articleDetail i': {
    'marginRight': [{ 'unit': 'px', 'value': 20 }],
    'color': '#aaa',
    'fontStyle': 'normal'
  },
  'tags': {
    'padding': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 1 }, { 'unit': 'px', 'value': 0 }],
    'borderTop': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#ccc' }],
    'borderBottom': [{ 'unit': 'px', 'value': 1 }, { 'unit': 'string', 'value': 'solid' }, { 'unit': 'string', 'value': '#ccc' }]
  },
  'tags span': {
    'marginRight': [{ 'unit': 'px', 'value': 14 }],
    'color': '#f04e37',
    'fontWeight': '400',
    'fontSize': [{ 'unit': 'px', 'value': 18 }]
  },
  'otherArticle a span': {
    'marginRight': [{ 'unit': 'px', 'value': 10 }]
  },
  'tags a': {
    'marginRight': [{ 'unit': 'px', 'value': 10 }]
  },
  'tags a': {
    'boxSizing': 'content-box',
    'padding': [{ 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 20 }, { 'unit': 'px', 'value': 6 }, { 'unit': 'px', 'value': 20 }],
    'borderRadius': '3px',
    'background': '#f04e37',
    'color': '#fff',
    'textOverflow': 'clip',
    'font': [{ 'unit': 'px', 'value': 400 }, { 'unit': 'string', 'value': 'medium/normal' }, { 'unit': 'string', 'value': 'Arial,Helvetica,sans-serif' }],
    'cursor': 'pointer'
  },
  'articleSource i': {
    'fontStyle': 'normal'
  },
  'download-link i': {
    'fontStyle': 'normal'
  },
  'otherArticle i': {
    'fontStyle': 'normal'
  },
  'articleSource': {
    'padding': [{ 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }],
    'color': '#666',
    'letterSpacing': [{ 'unit': 'px', 'value': 1 }],
    'fontSize': [{ 'unit': 'px', 'value': 14 }],
    'lineHeight': [{ 'unit': 'em', 'value': 1.5 }]
  },
  'otherArticle a': {
    'float': 'left',
    'overflow': 'hidden',
    'width': [{ 'unit': '%H', 'value': 0.5 }],
    'height': [{ 'unit': 'px', 'value': 30 }],
    'color': '#888',
    'textOverflow': 'ellipsis',
    'whiteSpace': 'nowrap',
    'lineHeight': [{ 'unit': 'px', 'value': 30 }],
    'cursor': 'pointer'
  },
  'myattachment': {
    'color': '#999',
    'cursor': 'pointer'
  },
  'otherArticle': {
    'height': [{ 'unit': 'px', 'value': 42 }]
  },
  'download-link': {
    'textAlign': 'center',
    'position': 'relative',
    'overflow': 'hidden',
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 9 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }],
    'padding': [{ 'unit': 'px', 'value': 14 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 14 }, { 'unit': 'px', 'value': 0 }],
    'height': [{ 'unit': 'px', 'value': 44 }],
    'borderRadius': '4px',
    'backgroundColor': '#f8f9f9',
    'fontWeight': '300',
    'fontSize': [{ 'unit': 'px', 'value': 1 }],
    'fontFamily': 'Open Sans,sans-serif',
    'lineHeight': [{ 'unit': 'px', 'value': 24 }],
    'borderLeftColor': '#3f4652',
    'textRendering': 'optimizelegibility'
  },
  'download-link img': {
    'width': [{ 'unit': 'px', 'value': 30 }],
    'height': [{ 'unit': 'px', 'value': 30 }]
  },
  'download-link a': {
    'verticalAlign': 'middle',
    'lineHeight': [{ 'unit': 'px', 'value': 44 }]
  },
  'download-link img': {
    'verticalAlign': 'middle',
    'lineHeight': [{ 'unit': 'px', 'value': 44 }]
  },
  'download-link a': {
    'marginRight': [{ 'unit': 'px', 'value': 1 }],
    'height': [{ 'unit': 'px', 'value': 44 }]
  },
  'download-link svg': {
    'width': [{ 'unit': 'px', 'value': 20 }],
    'height': [{ 'unit': 'px', 'value': 20 }],
    'verticalAlign': 'text-bottom',
    'lineHeight': [{ 'unit': 'px', 'value': 44 }],
    'fill': '#aaa'
  },
  'download-link i': {
    'marginRight': [{ 'unit': 'px', 'value': 2 }]
  },
  'en': {
    'fontFamily': 'arial,helvetica,sans-serif!important'
  },
  'en': {
    'wordBreak': 'keep-all'
  },
  'en p': {
    'wordBreak': 'keep-all'
  },
  'articleContent': {
    'overflow': 'hidden',
    'padding': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }, { 'unit': 'px', 'value': 0 }, { 'unit': 'px', 'value': 10 }],
    'color': '#888',
    'textAlign': 'justify',
    'textIndent': '0',
    'letterSpacing': [{ 'unit': 'px', 'value': 1 }],
    'fontWeight': '400',
    'lineHeight': [{ 'unit': 'em', 'value': 1.6 }]
  },
  'articleContent p': {
    'color': '#888!important',
    'lineHeight': [{ 'unit': 'px', 'value': 26 }],
    'fontFamily': '"Microsoft YaHei"!important',
    'fontSize': [{ 'unit': 'px', 'value': 16 }]
  },
  'articleContent span': {
    'color': '#888!important',
    'lineHeight': [{ 'unit': 'px', 'value': 26 }],
    'fontFamily': '"Microsoft YaHei"!important',
    'fontSize': [{ 'unit': 'px', 'value': 16 }]
  },
  'en p': {
    'color': '#888!important',
    'lineHeight': [{ 'unit': 'px', 'value': 26 }],
    'fontFamily': 'arial,helvetica,sans-serif!important',
    'fontSize': [{ 'unit': 'px', 'value': 18 }]
  },
  'en span': {
    'color': '#888!important',
    'lineHeight': [{ 'unit': 'px', 'value': 26 }],
    'fontFamily': 'arial,helvetica,sans-serif!important',
    'fontSize': [{ 'unit': 'px', 'value': 18 }]
  },
  'articleContent li': {
    'listStyle': 'disc'
  },
  'articleContent ol': {
    'listStyle': 'disc'
  },
  'articleContent ul': {
    'listStyle': 'disc'
  },
  'articleContent img': {
    'marginTop': [{ 'unit': 'px', 'value': 10 }]
  },
  'articleContent a': {
    'textDecoration': 'underline'
  },
  'articleContent p': {
    'marginBottom': [{ 'unit': 'px', 'value': 26 }]
  },
  'articleContent b': {
    'fontWeight': '700!important'
  },
  'articleContent em': {
    'fontWeight': '700!important'
  },
  'articleContent strong': {
    'fontWeight': '700!important'
  },
  'articleContent table': {
    'display': 'block',
    'margin': [{ 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }, { 'unit': 'px', 'value': 0 }, { 'unit': 'string', 'value': 'auto' }]
  }
});
