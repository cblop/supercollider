// read a soundfile

// now play it:
(
var k5  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick5.wav");
var k4  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick4.wav");
var k3  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick3.wav");
var k2  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick2.wav");
var k1  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick1.wav");



p = SynthDef("playfile",{arg out = 0, bufnum;
        Out.ar( out,
            Pan2.ar(PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum)), 0, 1)
        )
    //}).play(s,[\bufnum, k1.bufnum ]);
    });

k = SynthDef("kick", { arg bufs, vel = 1;
        p.play(s, [\bufnum, bufs[vel].bufnum]);
    });

)
var bufs = [k1,k2,k3,k4,k5]
k.play(s, [\bufs, bufs])

x.free; b.free;


// read a soundfile
//s.sendMsg(\b_allocRead, 15, "/home/matt/supercollider/drums/kick/kick5.wav");
//var b =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick5.wav");
(
var k5  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick5.wav");
var k4  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick4.wav");
var k3  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick3.wav");
var k2  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick2.wav");
var k1  =  Buffer.read(s, "/home/matt/supercollider/drums/kick/kick1.wav");
var kicks = [k1.bufnum, k2.bufnum, k3.bufnum, k4.bufnum, k5.bufnum];

SynthDef(\drums, {|out = 0, kickVel = 0, chatVel = 0, crashVel = 0, pan1 = 0, pan2 = 0, pan3 = 0, pan4 = 0|

    PlayBuf.ar(1, kicks[kickVel]);
}
)
)

