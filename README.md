Ini merupakan implementasi manageroauth
Token Store
1. MemoryToken
2. JDBCToken
3. CustomToken



Flow Grant Type User Password

Grant type ini biasanya digunakan bila pembuat aplikasi client sama dengan pembuat resource server. Sehingga aplikasi client diperbolehkan mengambil data username dan password langsung dari user. Contohnya: aplikasi Twitter android ingin mengakses daftar tweet untuk user tertentu. Walaupun demikian, penggunaan flow type ini tidak direkomendasikan lagi. Sebaiknya gunakan flow type authorization code atau client credentials.

    Jalankan Aplikasi authorization-server dan resource-server

    Akses url terproteksi tanpa login dulu

      curl http://localhost:10001/api/admin 

    Ini akan menghasilkan response code 401

    Request token dulu :

      curl -X POST -vu clientapp:123456 http://localhost:10000/oauth/token -H "Accept: application/json" -d "client_id=clientapp&grant_type=password&username=endy&password=123" 

    Ini akan menghasilkan response JSON berisi token, misalnya tokennya adalah initokensaya

    Akses lagi url terproteksi dengan menggunakan token :

      curl -H "Authorization: Bearer initokensaya" http://localhost:10001/api/admin

    Ini akan menghasilkan response sukses.




Flow Grant Type Client Credentials

Pada flow type ini, aplikasi client diberikan akses penuh terhadap resource yang diproteksi tanpa perlu meminta username dan password user. Biasanya digunakan bila aplikasi client dan aplikasi resource server dibuat oleh perusahaan yang sama.

    Jalankan aplikasi authorization-server dan resource-server

    Request token ke authorization-server dengan memasang client_id dan client_secret pada header dengan cara Basic Authentication

      curl -X POST -vu clientcred:123456 http://localhost:10000/oauth/token -H "Accept: application/json" -d "grant_type=client_credentials"

    Kita akan mendapatkan response berupa access_token dalam format JSON

      {
          "access_token":"45841c94-8851-4f93-bdb1-7de9519df175",
          "token_type":"bearer",
          "expires_in":43199,"scope":"trust"
      }

    Gunakan access token ini dalam Authorization header untuk mengakses resource-server

      curl -H "Authorization: Bearer 45841c94-8851-4f93-bdb1-7de9519df175" http://localhost:10001/api/client

    Sukses menghasilkan response

      {
          "sukses":true,
          "page":"client",
          "user":"clientcred"
      }


2.Flow Grant Type Crendential
