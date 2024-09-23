```toml
name = 'add pekerja'
method = 'POST'
url = '{{url-manpromanpro}}/pekerja/add'
sortWeight = 1000000
id = 'ecc51c05-ce2c-4ab0-a4fe-cfa338e1ec9e'

[body]
type = 'JSON'
raw = '''
{
  "nama": "Kang Seulgi",
  "usia": "30",
  "pekerjaan": "UI/UX Designer",
  "biografi": "Kang Seulgi adalah seorang UI/UX Designer yang berdedikasi, dengan fokus pada menciptakan pengalaman pengguna yang intuitif dan desain antarmuka yang menarik. Dengan keahlian dalam penelitian pengguna, wireframing, prototyping, dan desain visual, Seulgi menggabungkan estetika dengan fungsionalitas untuk menghasilkan solusi digital yang efektif dan memikat.",
  "listProyek": [
    "53171317-3e9a-4976-9b26-6ecdfdda765f"
  ]
}
'''
```
