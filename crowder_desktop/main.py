import tkinter as tk
from tkinter import ttk
from tkinter import *
from PIL import ImageTk, Image
from tkinter import messagebox
import pyrebase
import mysql.connector

root = tk.Tk()

# Δημιουργία style
style = ttk.Style(root)
root.tk.call('source', 'azure.tcl')
style.theme_use('azure')
root.option_add('*Font', "Arial")

#icon-ονομασία
root.title("Crowder")
root.iconbitmap("favicon.ico")

logo = ImageTk.PhotoImage(Image.open("crowder.png"))
label = ttk.Label(image=logo)
label.pack()

#διαστάσεις παραθύρου
window_width = 900
window_height = 700
screen_width = root.winfo_screenwidth()
screen_height = root.winfo_screenheight()
center_x = int(screen_width/2 - window_width / 2)
center_y = int(screen_height/2 - window_height / 2)
root.geometry(f'{window_width}x{window_height}+{center_x}+{center_y}')
root.resizable(False, False)

#firebase
firebaseConfig = {
    "apiKey": "AIzaSyDgH0lE99lAwzlwDra4kJ2mYj1Llsbsbi8",
    "authDomain": "crowder-54fe7.firebaseapp.com",
    "databaseURL":"https://console.firebase.google.com/u/0/project/crowder-54fe7/authentication/users/",
    "projectId": "crowder-54fe7",
    "storageBucket": "crowder-54fe7.appspot.com",
    "messagingSenderId": "49190602703",
    "appId": "1:49190602703:web:73eada9ca7a6a9294f34ba"
  }
firebase = pyrebase.initialize_app(firebaseConfig)
auth = firebase.auth()
storage = firebase.storage()
db = firebase.database()

#Σύνδεση με την sql
mydb = mysql.connector.connect(
  host="35.234.101.122",
  user="root",
  password="kwdikos123",
  database="crowder"
)

mycursor = mydb.cursor()


def login():
    # Σύνδεση
    global email, password
    ttk.Label(root, text="Email χρήστη:").pack()
    email = ttk.Entry(root, textvariable="email")
    email.pack()
    ttk.Label(root, text="").pack()
    ttk.Label(root, text="Κώδικός σύνδεσης:").pack()
    password = ttk.Entry(root, textvariable="password", show='●')
    password.pack()
    ttk.Label(root, text="").pack()
    # create Login Button
    button = tk.Button(root, text="Είσοδος", height="2", width="30", bg='#49A2F8', command=lambda: eisodos(root))
    button.pack()
    ttk.Label(root, text="").pack()
    ttk.Label(root, text="Δεν έχετε λογιαριασμό; Εγγραφείτε εδώ:", ).pack()
    button = ttk.Button(root, text="Εγγραφή", command=lambda: eggrafh(root))
    button.pack()
    root.bind('<Return>', eisodose)


def eisodos(root):
    try:
        login = auth.sign_in_with_email_and_password(email=email.get(), password=password.get())
        root.destroy()
        import arxikh
    except:
        messagebox.showwarning("Crowder", "Χμμ... κάτι δεν πήγε καλά,ξαναδοκίμασε να εισάγεις τα στοιχεία σου")


def eisodose(e):
    if ((email.get()!= '') and (password.get()!='')):
        try:
            login = auth.sign_in_with_email_and_password(email=email.get(), password=password.get())
            root.destroy()
            import arxikh
        except:
            messagebox.showwarning("Crowder", "Χμμ... κάτι δεν πήγε καλά,ξαναδοκίμασε να εισάγεις τα στοιχεία σου")


def eggrafh(root):
    root.destroy()
    import eggrafh

login()


root.mainloop()
