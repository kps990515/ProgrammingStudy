class FourCal:
    def setdata(self,a,b):
        self.a=a
        self.b=b
    def sum(self):
        return self.a+self.b
    def mul(self):
        return self.a*self.b
    def sub(self):
        return self.a-self.b
    def div(self):
        return self.a/self.b

a=FourCal()
a.setdata(4,2)
print(a.sum())
print(a.mul())
print(a.sub())
print(a.div())